package com.monofire.bestcertificate.views.text

import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText
import androidx.annotation.Nullable
import com.monofire.bestcertificate.R
import com.monofire.bestcertificate.models.CertificateItem

class CustomEdittext : androidx.appcompat.widget.AppCompatEditText {
    private var mShowText = ""

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(null)

    }

    private fun init(@Nullable set: AttributeSet?) {
        val a: TypedArray = context.obtainStyledAttributes(set, R.styleable.CustomEdittext)

        val text = a.getString(R.styleable.CustomEdittext_title).isNullOrEmpty()
        if (text) setText("boşş") else setText(a.getString(R.styleable.CustomEdittext_title))
        textSize=a.getInteger(R.styleable.CustomEdittext_title_position,0).toFloat()

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }
}