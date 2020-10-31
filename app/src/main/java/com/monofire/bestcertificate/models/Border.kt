package com.monofire.bestcertificate.models

import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import androidx.core.graphics.toRectF

data class Border(val rect: Rect, val borderPaint: Paint, val isVisible: Boolean = false) {
    fun rectToRectF(): RectF = rect.toRectF()
}
