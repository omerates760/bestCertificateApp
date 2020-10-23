package com.monofire.bestcertificate.save

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.widget.Toast
import com.monofire.bestcertificate.SampleView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.lang.RuntimeException
import java.util.stream.BaseStream

class SaveFile(
    private val view: SampleView,
    private val fileName: String,
    private val fileFormat: String, private val context: Context
) {
    lateinit var outPutStream: FileOutputStream

    private fun fileManagement() {
        val filepath: File? = context.externalCacheDir
        val dir = File(filepath?.absolutePath + "/Projects")
        dir.mkdir()
        val file = File(dir, System.currentTimeMillis().toString() + fileName)
        try {
            outPutStream = FileOutputStream(file)

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        viewToBitmap()?.compress(Bitmap.CompressFormat.PNG, 100, outPutStream)
        outPutStream.flush()
        try {
            outPutStream.close()
            Toast.makeText(context, "Dosya Kaydedildi.", Toast.LENGTH_SHORT).show()


        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context, "İşlem Hatası", Toast.LENGTH_SHORT).show()

        } catch (e: RuntimeException) {
            e.printStackTrace()
            Toast.makeText(
                context,
                "Kaydedilirken hata oluştu.Tekrar deneyiniz.",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

    private fun viewToBitmap(): Bitmap? {
        val result = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val c = Canvas(result)
        view.draw(c)
        return result
    }
}