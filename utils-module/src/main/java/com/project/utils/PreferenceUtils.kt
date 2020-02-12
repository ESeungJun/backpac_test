package com.project.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtils(context: Context) {

    private var preferences: SharedPreferences
    private var preEditor: SharedPreferences.Editor

    init {
        preferences = context.getSharedPreferences("TEMP", Context.MODE_PRIVATE)
        preEditor = preferences.edit()
    }


    var saveBoolean: Boolean
        get() = preferences.getBoolean(KEY_TEMP, false)
        set(value) {
            preEditor.run {
                putBoolean(KEY_TEMP, value)
                commit()
            }
        }


    var saveString: String
        get() = preferences.getString(KEY_TEMP, "")?: ""
        set(value) {
            preEditor.run {
                putString(KEY_TEMP, value)
                commit()
            }
        }


    var saveLong: Long
        get() = preferences.getLong(KEY_TEMP, 0L)
        set(value) {
            preEditor.run {
                putLong(KEY_TEMP, value)
                commit()
            }
        }



    var saveInt: Int
        get() = preferences.getInt(KEY_TEMP, 0)
        set(value) {
            preEditor.run {
                putInt(KEY_TEMP, value)
                commit()
            }
        }



    companion object {
        private val KEY_TEMP = "KEY_TEMP"


        private var instance: PreferenceUtils? = null

        fun getInstance(context: Context) = instance
            ?: PreferenceUtils(context).also { instance = it }
    }

}
