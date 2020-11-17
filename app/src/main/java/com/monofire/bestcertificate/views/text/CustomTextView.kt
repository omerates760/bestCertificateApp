package com.monofire.bestcertificate.views.text

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import com.monofire.bestcertificate.models.Border
import com.monofire.bestcertificate.models.LayoutTranslate
import com.monofire.bestcertificate.models.Text
import com.monofire.bestcertificate.models.TextBuilderSize


class CustomTextView {

    lateinit var staticLayout: StaticLayout
    lateinit var textBorder: Border
    lateinit var textBuilderSize: TextBuilderSize
    private var customText: Text
    var translate: LayoutTranslate
    private var baseX = 0
    private var baseY = 0
    var isVisible = false

    constructor(text: Text, translate: LayoutTranslate) {
        this.customText = text
        this.translate = translate
        this.baseX = translate.translateX!!
        this.baseY = translate.translateY!!
        createTextLayout()

    }

    private fun createTextLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            staticLayout = StaticLayout.Builder.obtain(
                customText.text,
                0,
                customText.text.length,
                customText.paint,
                800
            ).setMaxLines(4)
                .setAlignment(Layout.Alignment.ALIGN_CENTER).build()

        }
        textBuilderSize = TextBuilderSize(staticLayout.width, staticLayout.height)

        createTextLayoutBorder()
    }

    fun createTextLayoutBorder() {
        val borderPaint = Paint()
        borderPaint.reset()
        borderPaint.style = Paint.Style.STROKE
        borderPaint.strokeWidth = 1.8f
        borderPaint.isAntiAlias = true

        if (isVisible) {
            borderPaint.color = Color.BLACK
        } else {
            borderPaint.color = Color.TRANSPARENT
        }
        val rect = Rect(
            translate.translateX!!,
            translate.translateY!!,
            textBuilderSize.witdh + translate.translateX!!,
            textBuilderSize.height + translate.translateY!!
        )
        textBorder = Border(rect, borderPaint)


    }

    fun changeCoordinates(x: Int, y: Int) {
        translate.translateX = x
        translate.translateY = y
        textBorder.rect.left = x
        textBorder.rect.top = y
        textBorder.rect.right = x + textBuilderSize.witdh
        textBorder.rect.bottom = y + textBuilderSize.height

    }

    fun setText(text: String): String {
        customText.text = text
        return customText.text
    }

}