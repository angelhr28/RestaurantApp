package com.example.restaurantapp.ui.view.screen

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.restaurantapp.R
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testing() {

        val edtEmail = onView(withId(R.id.edtEmail))
        val edtPassword = onView(withId(R.id.edtPassword))
        val btLogin = onView(withId(R.id.btLogin))

        // Verificamos que inicialmente los campos están vacíos y el botón deshabilitado
        onView(withId(R.id.edtEmail)).check(matches(withText("")))
        onView(withId(R.id.edtPassword)).check(matches(withText("")))
        btLogin.check(matches(isNotEnabled()))

        // Insertamos los campos y verificamos el estado del botón
        edtEmail.perform(replaceText("angelhuamannahui@gmail.com"))
        edtPassword.perform(replaceText("nino3667193"))
        btLogin.check(matches(isEnabled()))

        // Validamos los campos y ejecutamos el logeo
        edtEmail.check(matches(withText("angelhuamannahui@gmail.com")))
        edtPassword.check(matches(withText("nino3667193")))
        btLogin.perform(click())
    }
}