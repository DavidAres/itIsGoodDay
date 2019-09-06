package com.example.itisgoodday.tools

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager {
    enum class Status {
        ERROR, SUCCESS
    }

    companion object {
        private const val PREFERENCES_NAME: String = "itIsGoodDay"
        private const val DEFAULT_ERROR_PREFERENCES_STRING: String = ""

        private lateinit var prefs: SharedPreferences
        private lateinit var edit: SharedPreferences.Editor

        fun save(context: Context?, key: String?, value: Any?): Status {
            val validData: Boolean = !key.isNullOrBlank()
                    && value != null
                    && context != null

            if (validData) {
                if (saveByValue(context!!, key!!, value!!)) {
                    return Status.SUCCESS
                }
            }

            return Status.ERROR
        }

        private fun saveByValue(context: Context, key: String, value: Any): Boolean {
            prefs = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            edit = prefs.edit()

            when (value) {
                is Int -> edit.putInt(key, value)
                is Boolean -> edit.putBoolean(key, value)
                is String -> edit.putString(key, value)
            }

            return edit.commit()
        }


        fun restore(context: Context, key: String?): Status {
            prefs = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)


            if (prefs != null && prefs.contains(key)) {
                return Status.SUCCESS
            }
            return Status.ERROR
        }



        fun restoreString(context: Context, key: String?): String? = when (restore(context, key)) {
            Status.SUCCESS -> {
                prefs.getString(key, DEFAULT_ERROR_PREFERENCES_STRING)
            }
            Status.ERROR -> {
                null
            }
        }

        fun deleteKeyFromPreferences(context: Context, key: String): Boolean = try {
            prefs = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            var edit = prefs.edit()
            edit.remove(key)
            edit.commit()
            true
        } catch (e: Exception) {
            false
        }
    }
}
