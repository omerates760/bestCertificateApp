package com.monofire.bestcertificate.views.text

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable

class Certificate(drawablePath: Int, context: Context) {
    var mImage: Bitmap = BitmapFactory.decodeResource(context.resources, drawablePath)

    fun getResizeBitmap(bitmap: Bitmap, reqWidth: Int, reqHeight: Int): Bitmap {
        val matrix = Matrix()
        val src = RectF(0F, 0F, bitmap.width.toFloat(), bitmap.height.toFloat())
        val dst = RectF(0F, 0F, reqWidth.toFloat(), reqHeight.toFloat())
        matrix.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER)
        return Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            bitmap.height,
            matrix,
            true
        )
    }
}