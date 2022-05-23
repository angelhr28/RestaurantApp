package com.example.restaurantapp.ui.view.screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.restaurantapp.data.model.UserModel
import com.example.restaurantapp.databinding.ActivityCheckInBinding
import com.example.restaurantapp.ui.viewmodel.UserViewModel
import com.example.restaurantapp.utils.*

class CheckInActivity : AppActivity() {

    private lateinit var binding: ActivityCheckInBinding
    private val userViewModel: UserViewModel by viewModels()

    private var email: String = ""
    private var password: String = ""
    private var fullName: String = ""
    private var phone: String = ""
    private val length = 9

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityCheckInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        observersBinding()
    }

    private fun checkIn() {
        hideKeyboardFrom(this, binding.root)
        val user = UserModel(email, fullName, phone, password)
        userViewModel.checkIn(user)
    }

    private fun isEnableButton(): Boolean {
        return isValidateEmail(email)
                && !isNullOrEmpty(email)
                && !isNullOrEmpty(password)
                && !isNullOrEmpty(phone)
                && !isNullOrEmpty(fullName)
                && password.length >= length
                && phone.length == length
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
            binding.root.makeSnackbar(it) {}
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
                btCheckIn.isEnabled = isEnableButton()
            }
            val tchPassword = afterTextChanged {
                password = edtPassword.text.toString().trim()
                tedtPassword.error = when {
                    password.length < length -> "La contraseña debe tener minimo 9 caracteres"
                    else -> null
                }
                btCheckIn.isEnabled = isEnableButton()
            }
            val tchPhone = afterTextChanged {
                phone = edtPhone.text.toString().trim()
                tedtPhone.error = when {
                    phone.length != length -> "El numero de celular es incorrecto"
                    else -> null
                }
                btCheckIn.isEnabled = isEnableButton()
            }
            val tchFullName = afterTextChanged {
                fullName = edtFullName.text.toString().trim()
                btCheckIn.isEnabled = isEnableButton()
            }
            edtEmail.addTextChangedListener(tchEmail)
            edtPassword.addTextChangedListener(tchPassword)
            edtPhone.addTextChangedListener(tchPhone)
            edtFullName.addTextChangedListener(tchFullName)
            btCheckIn.setOnClickListener { checkIn() }
            btBack.setOnClickListener { onBackPressed() }
        }
    }

    private fun reloadHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

}