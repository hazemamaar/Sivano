package com.android.sivano.data.local

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.google.gson.Gson

class ComplexPreferences @SuppressLint("CommitPrefEdits")
constructor(
    private val GSON: Gson,
    private val preferences: SharedPreferences,
    private val editor: SharedPreferences.Editor
    ) {
    fun putObject(key: String, obj: Any?) {
        if (obj == null) {
            throw IllegalArgumentException("object is null")
        }
        if (key == "") {
            throw IllegalArgumentException("key is empty or null")
        }
        try {
            editor!!.putString(key, GSON.toJson(obj))
        } catch (e: Exception) {
            println(e.message)
        }
    }

    fun <T> getObject(key: String, classItem: Class<T>): T? {
        val gson = preferences!!.getString(key, null)
        return if (gson == null) {
            null
        } else {
            try {
                GSON.fromJson(gson, classItem)
            } catch (e: Exception) {
                throw IllegalArgumentException("Object stored with key $key is instanceof other class")
            }
        }
    }

    fun putInteger(key: String, number: Int) {
        editor!!.putInt(key, number)
    }

    fun getInteger(key: String, number: Int): Int {
        return preferences!!.getInt(key, number)
    }

    fun putString(key: String, charSequence: String) {
        editor!!.putString(key, charSequence)
    }

    fun getString(key: String, charSequence: String = ""): String {
        return preferences!!.getString(key, charSequence)!!
    }

    fun putBoolean(key: String, state: Boolean) {
        editor!!.putBoolean(key, state)
    }

    fun getBoolean(key: String, state: Boolean): Boolean {
        return preferences!!.getBoolean(key, state)
    }

    fun clearAllSettings() {
        editor!!.clear()
        editor!!.commit()
    }

    fun commit() {
        editor!!.commit()
    }
}