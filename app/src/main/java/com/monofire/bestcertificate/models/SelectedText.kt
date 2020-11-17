package com.monofire.bestcertificate.models

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet

class SelectedText {

    constructor(
        text: String,
        textSize: Int,
        textType: String,
        textFont: String,
        textColor: Int,
        layoutTranslate: LayoutTranslate
    )

    constructor(text: String, layoutTranslate: LayoutTranslate) : super() {
    }

    constructor(textSize: Float, layoutTranslate: LayoutTranslate) : super() {
    }

    constructor(textFont: Typeface, layoutTranslate: LayoutTranslate) : super() {
    }

    constructor(textColor: Int, layoutTranslate: LayoutTranslate) : super() {
    }
}
