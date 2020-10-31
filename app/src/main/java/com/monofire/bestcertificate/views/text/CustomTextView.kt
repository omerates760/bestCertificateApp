package com.monofire.bestcertificate.views.text

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.monofire.bestcertificate.models.Border
import com.monofire.bestcertificate.models.LayoutTranslate
import com.monofire.bestcertificate.models.Text
import com.monofire.bestcertificate.models.TextBuilderSize

class CustomTextView {

    lateinit var staticLayout: StaticLayout
    lateinit var textBorder: Border
    lateinit var textBuilderSize: TextBuilderSize
    private var text: Text
    var translate: LayoutTranslate
    private var baseX = 0
    private var baseY = 0

    constructor(text: Text, translate: LayoutTranslate) {
        this.text = text
        this.translate = translate
        this.baseX = translate.translateX
        this.baseY = translate.translateY
        createTextLayout()

    }


    private fun createTextLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            staticLayout = StaticLayout.Builder.obtain(
                text.text,
                0,
                text.text.length,
                text.paint,
                800
            ).setMaxLines(4)
                .setAlignment(Layout.Alignment.ALIGN_CENTER).build()

        }
        textBuilderSize = TextBuilderSize(staticLayout.width, staticLayout.height)
        createTextLayoutBorder()
    }

    fun createTextLayoutBorder() {
        val borderPaint = Paint()
        borderPaint.style = Paint.Style.STROKE
        borderPaint.strokeWidth = 1.8f
        borderPaint.color = Color.BLACK
        borderPaint.isAntiAlias = true
        val rect = Rect(
            translate.translateX,
            translate.translateY,
            textBuilderSize.witdh + translate.translateX,
            textBuilderSize.height + translate.translateY
        )
        Log.e("LEFT", "" + translate.translateX)
        Log.e("TOP", "" + translate.translateY)
        Log.e("RİGHT", "${translate.translateX + textBuilderSize.witdh}")
        Log.e("BOTTOM", "${translate.translateY + textBuilderSize.height}")

        textBorder = Border(rect, borderPaint)
    }

    fun changeCoordinates(x: Int, y: Int) {
        translate.translateX=x
        translate.translateY=y
        textBorder.rect.left = x
        textBorder.rect.top = y
        textBorder.rect.right = x + textBuilderSize.witdh
        textBorder.rect.bottom = y + textBuilderSize.height

    }

}