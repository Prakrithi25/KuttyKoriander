package com.example.kuttykoriander.data

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("little_lemon_prefs", Context.MODE_PRIVATE)

    var firstName: String
        get() = prefs.getString(KEY_FIRST_NAME, "") ?: ""
        set(value) = prefs.edit().putString(KEY_FIRST_NAME, value).apply()

    var lastName: String
        get() = prefs.getString(KEY_LAST_NAME, "") ?: ""
        set(value) = prefs.edit().putString(KEY_LAST_NAME, value).apply()

    var email: String
        get() = prefs.getString(KEY_EMAIL, "") ?: ""
        set(value) = prefs.edit().putString(KEY_EMAIL, value).apply()

    var isLoggedIn: Boolean
        get() = prefs.getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) = prefs.edit().putBoolean(KEY_IS_LOGGED_IN, value).apply()

    fun clear() {
        prefs.edit().clear().apply()
    }

    companion object {
        private const val KEY_FIRST_NAME = "first_name"
        private const val KEY_LAST_NAME = "last_name"
        private const val KEY_EMAIL = "email"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }
}
