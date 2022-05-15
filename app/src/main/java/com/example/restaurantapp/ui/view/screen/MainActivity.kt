package com.example.restaurantapp.ui.view.screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.restaurantapp.databinding.ActivityMainBinding
import com.example.restaurantapp.ui.viewmodel.UserViewModel
import com.example.restaurantapp.utils.*

class MainActivity : AppActivity() {

    private lateinit var binding: ActivityMainBinding
    private val userViewModel: UserViewModel by viewModels()

    private var email: String = ""
    private var password: String = ""
    private val length: Int = 9

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        observersBinding()
    }

    private fun checkInUI() {
        val intent = Intent(this, CheckInActivity::class.java)
        startActivity(intent)
    }

    private fun logIn() {
        hideKeyboardFrom(this, binding.root)
        userViewModel.logIn(email, password)
    }

    public override fun onStart() {
        super.onStart()
        userViewModel.isLogIn()
    }

    private fun isEnableButton(): Boolean {
        return isValidateEmail(email)
                && !isNullOrEmpty(email)
                && !isNullOrEmpty(password)
                && password.length >= length
    }

    private fun observersBinding() {
        userViewModel.isProgress.observe(this) {
            binding.apply {
                progress.isVisible = it
                backgroundProgress.isVisible = it
            }
        }
        userViewModel.isLogIn.observe(this) {
            reloadHome()
        }
        userViewModel.snackbar.observe(this) {
            binding.root.showSimpleSnackBar(it, this) {}
        }
    }

    private fun init() {
        binding.apply {
            val tchEmail = afterTextChanged {
                email = edtEmail.text.toString().trim()
                tedtEmail.error = when {
                    !isValidateEmail(email) -> "El formato del correo es incorrecto"
                    else -> null
                }
                btLogin.isEnabled = isEnableButton()
            }
            val tchPassword = afterTextChanged {
                password = edtPassword.text.toString().trim()
                tedtPassword.error = when {
                    password.length < length -> "La contraseña debe tener minimo 9 caracteres"
                    else -> null
                }
                btLogin.isEnabled = isEnableButton()
            }
            edtEmail.addTextChangedListener(tchEmail)
            edtPassword.addTextChangedListener(tchPassword)
            btCheckIn.setOnClickListener { checkInUI() }
            btLogin.setOnClickListener { logIn() }
        }
    }

    private fun reloadHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}