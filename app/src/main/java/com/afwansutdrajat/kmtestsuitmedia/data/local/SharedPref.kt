package com.afwansutdrajat.kmtestsuitmedia.data.local

import android.content.Context
import android.content.SharedPreferences

object SharedPref {
    private const val SHARED_PREF_NAME = "data_user"
    private const val DATA_NAME = "name"
    private const val DATA_SELECTED_NAME = "selected_name"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setName(context: Context, data: String) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(DATA_NAME, data)
        editor.apply()
    }

    fun getName(context: Context): String {
        return getSharedPreferences(context).getString(DATA_NAME, "") ?: ""
    }

    fun setSelectedName(context: Context, data: String) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(DATA_SELECTED_NAME, data)
        editor.apply()
    }

    fun getSelectedName(context: Context): String {
        return getSharedPreferences(context).getString(DATA_SELECTED_NAME, "") ?: ""
    }

    fun clearData(context: Context) {
        val editor = getSharedPreferences(context).edit()
        editor.remove(DATA_SELECTED_NAME)
        editor.apply()
    }
}