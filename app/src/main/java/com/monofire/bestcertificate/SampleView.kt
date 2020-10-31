package com.monofire.bestcertificate

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
import com.monofire.bestcertificate.models.LayoutTranslate
import com.monofire.bestcertificate.models.Text
import com.monofire.bestcertificate.views.text.Certificate
import com.monofire.bestcertificate.views.text.CustomTextView


class SampleView : View {

    lateinit var titleText: CustomTextView
    lateinit var certificate: Certificate
    val text = "Certificate of Achievement created by omerates"
    lateinit var mPaintText: TextPaint
    lateinit var mStaticLayout: StaticLayout
    val savedMatrix = Matrix()

    lateinit var mRectF: RectF
    lateinit var mRect: Rect
    lateinit var border: Rect
    lateinit var borderColor: Paint
    var isVisible = false
    var www = 400
    var hhh = 200

    val www1 = 400
    val hhh1 = 200


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
        val paint = TextPaint(Paint.ANTI_ALIAS_FLAG)
        paint.textSize = 45f
        paint.color = Color.BLUE

        val typeface: Typeface = Typeface.create("asasa", Typeface.NORMAL)
        val title = Text("Merhaba ben Ömer Ateş. Uygulama Başarılı. Umarım ileride başarılı bir sertifika uygulaması olacaktır.İnanıyorum ben.", typeface, "Title", paint)
        val titleTranslate = LayoutTranslate(450, 600)
        titleText = CustomTextView(title, titleTranslate)

        ////////////////////////////////////////////////////////////////

        certificate = Certificate(R.drawable.exam, context)
        /* mRect = Rect()
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
 */

        //Certificate change resize while load view.
        viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                certificate.mImage = certificate.getResizeBitmap(certificate.mImage, width, height)
            }
        })
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
        savedMatrix.postTranslate(
            titleText.translate.translateX.toFloat(),
            titleText.translate.translateY.toFloat()
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            canvas?.withMatrix(savedMatrix) {
                titleText.staticLayout.draw(canvas)


            }
            canvas?.drawRect(titleText.textBorder.rect, titleText.textBorder.borderPaint)

        }


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val value = super.onTouchEvent(event)
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (titleText.textBorder.rect.left < event.x && titleText.textBorder.rect.right > event.x && titleText.textBorder.rect.top < event.y && titleText.textBorder.rect.bottom > event.y) {
                    Log.e("durum", "içerde")
                    isVisible = true
                } else {
                    isVisible = false
                    Log.e("durum", "dışarda")
                }
                return true

            }
            MotionEvent.ACTION_UP -> {
                isVisible = false
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                if (isVisible) {
                    var currentX = event.x - titleText.staticLayout.width / 2
                    var currentY = event.y - titleText.staticLayout.height / 2
                    savedMatrix.reset()

                    titleText.changeCoordinates(currentX.toInt(), currentY.toInt())

                    //ui daha hızlı sonuç verecektir.
                    invalidate()

                }
                return true


            }
        }
        return value
    }

}