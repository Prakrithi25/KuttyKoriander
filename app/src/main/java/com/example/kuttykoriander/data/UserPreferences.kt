package com.example.kuttykoriander.data

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("kutty_koriander_prefs", Context.MODE_PRIVATE)

    var firstName: String
        get() = prefs.getString(KEY_FIRST_NAME, "") ?: ""
        set(value) = prefs.edit().putString(KEY_FIRST_NAME, value).apply()

    var lastName: String
        get() = prefs.getString(KEY_LAST_NAME, "") ?: ""
        set(value) = prefs.edit().putString(KEY_LAST_NAME, value).apply()

    var email: String
        get() = prefs.getString(KEY_EMAIL, "") ?: ""
        set(value) = prefs.edit().putString(KEY_EMAIL, value).apply()

    var phone: String
        get() = prefs.getString(KEY_PHONE, "+91 98765 43210") ?: "+91 98765 43210"
        set(value) = prefs.edit().putString(KEY_PHONE, value).apply()

    var spiceLevel: String
        get() = prefs.getString(KEY_SPICE_LEVEL, "Medium") ?: "Medium"
        set(value) = prefs.edit().putString(KEY_SPICE_LEVEL, value).apply()

    var kitchenLoop: Boolean
        get() = prefs.getBoolean(KEY_KITCHEN_LOOP, true)
        set(value) = prefs.edit().putBoolean(KEY_KITCHEN_LOOP, value).apply()

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
        private const val KEY_PHONE = "phone"
        private const val KEY_SPICE_LEVEL = "spice_level"
        private const val KEY_KITCHEN_LOOP = "kitchen_loop"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }
}
