package com.monofire.bestcertificate.save

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.monofire.bestcertificate.models.SelectedText
import com.monofire.bestcertificate.models.Text

object SharedText {
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "user-data"

    fun editTextProperties(context: Context, sharedTextProperties: SelectedText) {

        val jsonData = Gson()
        val saveData = jsonData.toJson(sharedTextProperties)
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val editor = sharedPreferences.edit()
        editor.putString("sharedTextProperties", saveData)
        editor.apply()
    }

    fun setSelectedTextType(context: Context, text: String) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val editor = sharedPreferences.edit()
        editor.putString("selectedType", text)
        editor.apply()
    }

    fun getSelectedTextType(context: Context): String {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        return sharedPreferences.getString("selectedType", "").toString()

    }

    fun getTextProperties(context: Context): SelectedText? {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val gson = Gson()
        val getData = sharedPreferences.getString("sharedTextProperties", "")
        return gson.fromJson(getData, SelectedText::class.java)
    }

}