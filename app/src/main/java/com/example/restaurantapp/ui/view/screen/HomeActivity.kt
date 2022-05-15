package com.example.restaurantapp.ui.view.screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.ActivityHomeBinding
import com.example.restaurantapp.databinding.NavHeaderBinding
import com.example.restaurantapp.ui.viewmodel.UserViewModel
import com.example.restaurantapp.utils.AppActivity

class HomeActivity : AppActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        observerBinding()
    }

    private fun observerBinding() {
        userViewModel.profile()
        userViewModel.user.observe(this) {
            val viewHeader = binding.navView.getHeaderView(0)
            val headerBinding = NavHeaderBinding.bind(viewHeader)
            headerBinding.username.text = it.fullName
            headerBinding.email.text = it.email
        }

        userViewModel.isLogOut.observe(this) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun init() {
        binding.apply {

            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

            setSupportActionBar(viewHome.toolbar)

            navController = navHostFragment.navController

            navView.setupWithNavController(navController)
            appBarConfiguration = AppBarConfiguration(
                setOf(R.id.nav_request, R.id.nav_report, R.id.nav_sales),
                drawerLayout)

            NavigationUI.setupActionBarWithNavController(this@HomeActivity,
                navController,
                appBarConfiguration)


            val logoutItem = navView.menu.findItem(R.id.nav_log_out)
            logoutItem.setOnMenuItemClickListener {
                logOut()
                return@setOnMenuItemClickListener true
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun logOut() {
        userViewModel.logOut()
    }

}