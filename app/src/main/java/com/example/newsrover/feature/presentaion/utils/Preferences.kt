package com.example.newsrover.feature.presentaion.utils

import android.content.Context
import android.content.SharedPreferences

object Preferences {
    fun saveString(context: Context, key: String, value: String) {
        val sharedPref: SharedPreferences = context.getSharedPreferences(Constants.NEWS_PREFERENCES, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(context: Context, key: String, defaultValue: String): String? {
        val sharedPref: SharedPreferences = context.getSharedPreferences(Constants.NEWS_PREFERENCES, Context.MODE_PRIVATE)
        return sharedPref.getString(key, defaultValue)
    }
}