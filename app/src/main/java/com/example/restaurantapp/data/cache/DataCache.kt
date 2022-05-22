package com.example.restaurantapp.data.cache

import android.content.SharedPreferences
import com.example.restaurantapp.domain.model.User
import com.example.restaurantapp.utils.enums.Preferences
import com.example.restaurantapp.utils.parseJson
import com.example.restaurantapp.utils.toJson
import javax.inject.Inject

class DataCache @Inject constructor(
    private val preferences: SharedPreferences,
) {
    fun clearUserCache() {
        preferences.edit().clear().commit()
    }

    fun saveUserCache(user: User?) {
        val pref = preferences.edit()
        pref.putString(Preferences.USER.key, user?.toJson())
        pref.commit()
    }

    fun getUserCache(): User? {
        val profile = preferences.getString(Preferences.USER.key, "")
        return profile?.let { parseJson(it) } ?: kotlin.run { null }
    }
}