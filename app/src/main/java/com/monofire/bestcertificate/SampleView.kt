package com.monofire.bestcertificate

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.StaticLayout.Builder
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import androidx.annotation.Nullable
import androidx.core.graphics.values
import androidx.core.graphics.withMatrix
import com.monofire.bestcertificate.views.text.Certificate
import com.monofire.bestcertificate.views.text.CustomText
import kotlin.math.sqrt


class SampleView : View {

    lateinit var titleText: CustomText
    lateinit var certificate: Certificate
    val text = "Certificate of Achievement created by omerates"
    lateinit var mPaintText: TextPaint
    lateinit var mStaticLayout: StaticLayout
    val savedMatrix = Matrix()
    val borderMatrix = Matrix()
    lateinit var mRectF: RectF
    lateinit var mRect: Rect
    lateinit var border: Rect
    lateinit var borderColor: Paint
    var www = 300
    var hhh = 500

    val www1 = 300
    val hhh1 = 500


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

    private fun init(@Nullable set: AttributeSet?) {

        ////////////////////////////////////////////////////////////////

        titleText = CustomText()
        certificate = Certificate(R.drawable.exam, context)
        mRect = Rect()
        mPaintText = TextPaint(Paint.ANTI_ALIAS_FLAG)

        mPaintText.color = Color.GREEN
        mPaintText.textSize = 70F

        mRectF = RectF()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mStaticLayout =
                Builder.obtain(
                    text.toUpperCase(),
                    0,
                    text.length,
                    mPaintText,
                    600
                ).setMaxLines(4)
                    .setAlignment(Layout.Alignment.ALIGN_CENTER).build()
        }
        border = Rect(
            www - www1,
            hhh - hhh1,
            mStaticLayout.width + www - www1,
            mStaticLayout.height + hhh - hhh1
        )
        borderColor = Paint(Paint.ANTI_ALIAS_FLAG)

        borderColor.style = Paint.Style.STROKE
        borderColor.color = Color.RED
        borderColor.strokeWidth = 1.5f


        //Certificate change resize while load view.
        viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                certificate.mImage = certificate.getResizeBitmap(certificate.mImage, width, height)
            }
        })
    }

    private fun rectToRectF(rect: Rect): RectF {
        return RectF(
            rect.left.toFloat(),
            rect.top.toFloat(),
            rect.right.toFloat(),
            rect.bottom.toFloat()
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        //Drawing image at bitmap
        canvas?.drawBitmap(certificate.mImage, 0f, 0f, null)

        //savedMatrix.mapRect(rectToRectF(mRect))
        savedMatrix.postTranslate(www.toFloat(), hhh.toFloat())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            canvas?.withMatrix(savedMatrix) {
                canvas?.drawRect(border, borderColor)
                mStaticLayout.draw(canvas)

            }
            //canvas.withMatrix(borderMatrix) {  }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val value = super.onTouchEvent(event)
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e("click1", "${savedMatrix.values().toMutableList()}}")
                Log.e(
                    "matrix",
                    "${border.left}...${border.right}...${border.top}...${border.bottom}"
                )

                return true
            }

            MotionEvent.ACTION_MOVE -> {
                var currentX = event.x - mStaticLayout.width / 2
                var currentY = event.y - mStaticLayout.height / 2
                savedMatrix.reset()
                www = currentX.toInt()
                hhh = currentY.toInt()
                invalidate()
                return true
            }
        }
        return value
    }

}