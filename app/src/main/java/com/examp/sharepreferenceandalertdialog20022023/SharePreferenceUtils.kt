package com.examp.sharepreferenceandalertdialog20022023

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by pphat on 3/27/2023.
 */

class SharePreferenceUtils(
    context: Context
) : Builder {
    private var sharedPreferences: SharedPreferences
    private var editor: SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences(
            "App_cache",
            AppCompatActivity.MODE_PRIVATE
        )

        editor = sharedPreferences.edit()
    }

    override fun setStringValue(key: String, value: String): Builder {
        editor.putString(key, value)
        return this
    }

    override fun setBooleanValue(key: String, value: Boolean): Builder {
        editor.putBoolean(key, value)
        return this
    }

    override fun removeKey(key: String): Builder {
        editor.remove(key)
        return this
    }

    override fun apply() {
        editor.apply()
    }

    fun getStringValue(key: String) = sharedPreferences.getString(key, "")

    fun getBooleanValue(key: String) = sharedPreferences.getBoolean(key, false)

    fun clear() {
        editor.clear().apply()
    }
}

interface Builder {
    fun setStringValue(key: String, value: String): Builder
    fun setBooleanValue(key: String, value: Boolean): Builder
    fun removeKey(key: String): Builder
    fun apply()
}
