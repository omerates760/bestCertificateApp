package com.monofire.bestcertificate.views.text

import android.content.Context
import android.graphics.*
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import androidx.annotation.Nullable
import com.monofire.bestcertificate.R

class BoundsView : View {
    lateinit var border: Rect
    lateinit var borderColor: Paint
    lateinit var paintTextPaint: TextPaint
    private val text = "Selamlar benim adım ömer.Bu bir örnek uygulama"
    private var ww: Float = 100F
    private var hh: Float = 900F
    private lateinit var mStaticLayout: StaticLayout

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    ) {
        init(attributeSet)
    }

    constructor(
        context: Context,
        attributeSet: AttributeSet,
        defStyle: Int,
        defStyleRes: Int
    ) : super(context, attributeSet, defStyle, defStyleRes) {
        init(attributeSet)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private fun init(@Nullable set: AttributeSet?) {
        border = Rect()
        borderColor = Paint(Paint.ANTI_ALIAS_FLAG)
        borderColor.style = Paint.Style.STROKE
        borderColor.color = Color.RED


        paintTextPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
        paintTextPaint.color = Color.BLACK
        paintTextPaint.textSize = 50f
        paintTextPaint.ascent()
        paintTextPaint.getTextBounds(text, 0, text.length, border)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            mStaticLayout = StaticLayout.Builder.obtain(text, 0, text.length, paintTextPaint, 400)
                .setMaxLines(3).setAlignment(Layout.Alignment.ALIGN_NORMAL).build()
        }


        border.top = border.top + hh.toInt()
        border.left = border.left + ww.toInt()
        border.right = border.left + border.right
        border.bottom = +border.top + border.bottom + paintTextPaint.textSize.toInt()


    }


    override fun onDraw(canvas: Canvas?) {

        mStaticLayout.draw(canvas)
        canvas?.drawText(text, 0, text.length, ww, hh, paintTextPaint)
        canvas?.drawRect(border, borderColor)
        Log.e("wwwww", "${paintTextPaint.measureText(text)}")
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var value = super.onTouchEvent(event)

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                val a = event.x.toInt()
                val b = event.y.toInt()
                Log.e("X-Y", "$a...$b")
                if (border.left < a && border.right > a && border.top < b && border.bottom > b) {

                    Log.e(
                        "border",
                        "${border.left}...${border.right}...${border.top}...${border.bottom}"
                    )

                    return true
                }
                return value
            }
        }

        return value
    }
}