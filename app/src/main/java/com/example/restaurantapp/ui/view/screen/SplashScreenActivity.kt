package com.example.restaurantapp.ui.view.screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.restaurantapp.R
import com.example.restaurantapp.ui.viewmodel.UserViewModel
import com.example.restaurantapp.utils.AppActivity

class SplashScreenActivity : AppActivity() {
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        observersBinding()
    }

    public override fun onStart() {
        super.onStart()
        userViewModel.isLogIn()
    }

    private fun observersBinding() {
        userViewModel.isLogIn.observe(this) {
            Log.e("TAG", "observersBinding: $it " )
            if (it) reloadHome() else reloadLogin()
        }
    }

    private fun reloadHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun reloadLogin() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}