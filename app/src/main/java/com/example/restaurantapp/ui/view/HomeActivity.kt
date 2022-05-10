package com.example.restaurantapp.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.ActivityHomeBinding
import com.example.restaurantapp.databinding.NavHeaderBinding
import com.example.restaurantapp.ui.viewmodel.UserViewModel
import com.example.restaurantapp.utils.AppActivity


class HomeActivity : AppActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        observerBinding()
    }

    private fun observerBinding() {
        userViewModel.isLogOut.observe(this) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun init() {
        binding.apply {
            setSupportActionBar(viewHome.toolbar)
            viewHome.toolbar.setNavigationOnClickListener {
                drawarLayout.openDrawer(GravityCompat.START)
            }
            val viewHeader = binding.navView.getHeaderView(0)
            val headerBinding = NavHeaderBinding.bind(viewHeader)
            headerBinding.username.text = ""
            headerBinding.email.text = ""

            navView.setNavigationItemSelectedListener { menuItem ->
                menuItem.isChecked = true
                when (menuItem.itemId) {
                    R.id.nav_log_out -> logOut()
                }

                val title = menuItem.title.toString()
                selectItem(title)
                true
            }
        }
    }

    private fun selectItem(title: String) {
        // Enviar título como arguemento del fragmento
//        val args = Bundle()
//        args.putString(PlaceholderFragment.ARG_SECTION_TITLE, title)
//        val fragment: Fragment = PlaceholderFragment.newInstance(title)
//        fragment.setArguments(args)
//        val fragmentManager: FragmentManager = supportFragmentManager
//        fragmentManager
//            .beginTransaction()
//            .replace(R.id.main_content, fragment)
//            .commit()
        binding.drawarLayout.closeDrawers() // Cerrar drawer
        setTitle(title)
    }

    private fun logOut() {
        userViewModel.logOut()
    }
}