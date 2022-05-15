package com.example.restaurantapp.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.restaurantapp.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.gson.Gson


fun setColorToStatusBar(activity: Activity, color: Int = Color.WHITE) {
    val window = activity.window
    val hsv = FloatArray(3)
    var darkColor: Int = color

    Color.colorToHSV(darkColor, hsv)
    hsv[2] *= 0.8f
    darkColor = Color.HSVToColor(hsv)

    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.statusBarColor = darkColor
}

fun calculatorPxToDps(context: Context, pixels: Int): Int {
    val scale = context.resources.displayMetrics.density
    return (pixels * scale + 0.5f).toInt()
}

fun dialogDefault(
    ctx: Context,
    layout: Int,
    width: Int? = null,
    height: Int? = null,
    function: (View, AlertDialog) -> Unit,
) {

    val widthDialog =
        width?.let { calculatorPxToDps(ctx, it) } ?: WindowManager.LayoutParams.WRAP_CONTENT
    val heightDialog =
        height?.let { calculatorPxToDps(ctx, it) } ?: WindowManager.LayoutParams.WRAP_CONTENT
    val dialog = AlertDialog.Builder(
        ctx,
        R.style.Theme_RestaurantApp
    )

    val view = (ctx as Activity).layoutInflater.inflate(layout, null)
    val customDialog = dialog.create()
    customDialog.apply {
        window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        setView(view)
        function(view, this)
        show()
        window?.setLayout(widthDialog, heightDialog)
    }
}

fun isNullOrEmpty(text: Any): Boolean {
    return when (text) {
        is String -> text.trim().isEmpty()
        is EditText -> text.text.trim().isEmpty()
        else -> false
    }
}

fun View.showSimpleSnackBar(
    message: String,
    ctx: Context,
    duration: Int = Snackbar.LENGTH_LONG,
    margin: Int = 20,
    function: (Snackbar) -> Unit,
) {
    val snackbar = Snackbar.make(this, message, duration)
    val view = snackbar.view
    val textview = view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
    textview.setTextColor(ContextCompat.getColor(ctx, R.color.primary))
    view.elevation = 120.0f
    val background = ContextCompat.getColor(ctx, R.color.white)
    view.setBackgroundColor(background)
    val marginSnackbar: ViewGroup.MarginLayoutParams =
        view.layoutParams as ViewGroup.MarginLayoutParams
    marginSnackbar.setMargins(20, 0, 20, calculatorPxToDps(ctx, margin))
    function(snackbar)
    snackbar.show()
}

fun isValidateEmail(email: String): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}

fun afterTextChanged(function: (s: Editable) -> Unit): TextWatcher {
    return object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            function(s)
        }
    }
}

fun toast(message: String, context: Context) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_SHORT
    ).show()
}

fun exceptionFirebase(exception: Exception?): String? {
    return when (exception) {
        is FirebaseAuthWeakPasswordException -> "La contraseña no cumple con la seguridad requerida."
        is FirebaseAuthInvalidCredentialsException -> "Contraseña invalida."
        is FirebaseAuthUserCollisionException -> "La cuenta ya se encuentra en uso en otro dispositivo."
        is FirebaseAuthInvalidUserException -> "El usuarios no esta registrado."
        is Exception -> exception.message
        else -> null
    }
}

fun hideKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = activity.currentFocus
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun hideKeyboardFrom(context: Context, view: View) {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Any.toJson(): String = Gson().toJson(this)

inline fun <reified T : Any> parseJson(json: String): T {
    val gson = Gson()
    return gson.fromJson(json, T::class.java)
}