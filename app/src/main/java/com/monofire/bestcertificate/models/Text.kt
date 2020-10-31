package com.monofire.bestcertificate.models

import android.graphics.Typeface
import android.text.TextPaint
import androidx.core.widget.TextViewCompat

data class Text(
    var text: String,
    var textFont: Typeface,
    val textType: String,
    var paint:TextPaint
)