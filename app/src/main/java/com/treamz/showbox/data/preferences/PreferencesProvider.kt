package com.treamz.showbox.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.treamz.showbox.domain.models.language.Language

class PrefRepository(val context: Context) {

    private val pref: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    private val editor = pref.edit()
    private val gson = Gson()

    private fun String.put(long: Long) {
        editor.putLong(this, long)
        editor.commit()
    }

    private fun String.put(int: Int) {
        editor.putInt(this, int)
        editor.commit()
    }

    private fun String.put(string: String) {
        editor.putString(this, string)
        editor.commit()
    }

    private fun String.put(boolean: Boolean) {
        editor.putBoolean(this, boolean)
        editor.commit()
    }

    private fun String.getLong() = pref.getLong(this, 0)

    private fun String.getInt() = pref.getInt(this, 0)

    private fun String.getString() = pref.getString(this, "")!!

    private fun String.getBoolean() = pref.getBoolean(this, false)

    fun getLanguage(): Language {
        PREF_CHOOSED_LANGUAGE.getString().let {
            if (it.isBlank()) {
                return gson.fromJson(
                    "{\"code\":\"en-US\",\"name\":\"English\"}",
                    Language::class.java
                )
            } else {
                return gson.fromJson(it, Language::class.java)
            }
        }
    }

    fun setLanguage(language: Language) {
        PREF_CHOOSED_LANGUAGE.put(gson.toJson(language))
    }

}


const val PREFERENCE_NAME = "MY_APP_PREF"

const val PREF_LOGGED_IN = "PREF_LOGGED_IN"
const val PREF_IS_LANGUAGE_SELECTED = "PREF_IS_LANGUAGE_SELECTED"
const val PREF_CURRENT_SELECTED_LANGUAGE = "PREF_CURRENT_SELECTED_LANGUAGE"
const val PREF_CONTACT_EMAIL = "PREF_CONTACT_EMAIL"
const val PREF_SHARE_MESSAGE = "PREF_SHARE_MESSAGE"
const val PREF_MINIMUM_APP_VERSION = "PREF_MINIMUM_APP_VERSION"

const val PREF_CHOOSED_LANGUAGE = "PREF_CHOOSED_LANGUAGE"
