package com.monofire.bestcertificate.db

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.monofire.bestcertificate.models.Certificate
import java.io.IOException

class LocalDatabase(private val context: Context) {
    private var arr = mutableListOf<Certificate>()
    private val data = getJsonDataFromAsset(context, "certificate.json")
    private val listPersonType = object : TypeToken<List<Certificate>>() {}.type

    fun readFile(): MutableList<Certificate> {
        arr = Gson().fromJson(data, listPersonType)
        return arr
    }

    fun readSelectedCertificateList(certificateId: String): MutableList<Certificate> {
        return arr.filter { certificate -> certificate.categoryId == certificateId } as MutableList<Certificate>
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