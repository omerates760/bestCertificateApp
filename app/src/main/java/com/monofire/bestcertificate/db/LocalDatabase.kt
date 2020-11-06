package com.monofire.bestcertificate.db

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.monofire.bestcertificate.models.Certificate
import java.io.IOException

class LocalDatabase(private val context: Context) {
    private var arr = mutableListOf<Certificate>()
    private var json = ""

    init {
        readFile()
    }

    fun readFile(): MutableList<Certificate> {
        val data = getJsonDataFromAsset(context, "certificate.json")

        val listPersonType = object : TypeToken<List<Certificate>>() {}.type
        var persons: MutableList<Certificate> = Gson().fromJson(data, listPersonType)
        return persons
    }

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}