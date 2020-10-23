package com.monofire.bestcertificate

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import androidx.annotation.Nullable


class SampleView : View {

    //var mImage: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.canvas)
    private val SQUARE_SIZE_DEF = 200

    lateinit var mRectSquare: Rect

    lateinit var mPaintSquare: Paint
    lateinit var mPaintCircle: Paint
    lateinit var mPaintText: Paint

    var mSquareColor: Int = Color.GREEN
    var mSquareSize: Int = SQUARE_SIZE_DEF

    private var mCircleX: Float = 0.0f
    private var mCircleY: Float = 0.0f
    private var mCircleRadius = 100f

    val text = "Merhaba Dünyaaaammmm"
    lateinit var mRectText: Rect
    private var mTextX: Float = 200f
    private var mTextY: Float = 200f

    private lateinit var mImage: Bitmap

    private fun customInit() {
        viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                viewTreeObserver.removeOnGlobalLayoutListener(this)
                mImage = getResizeBitmap(mImage, width, height)
            }

        })
    }

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

        Log.e("Drawing0", "$mTextX....$mTextY")

        mRectSquare = Rect()
        mPaintSquare = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaintSquare.color = Color.GREEN

        mPaintCircle = Paint()
        mPaintCircle.isAntiAlias = true
        mPaintCircle.color = Color.parseColor("#00ccff")

        //İnitialize Some Text
        mRectText = Rect()
        mPaintText = Paint()
        mPaintText.textSize = 50F
        mPaintText.color = Color.BLACK

        mImage = BitmapFactory.decodeResource(resources, R.drawable.canvas)
        viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                mImage = getResizeBitmap(mImage, width, height)

            }

        })

        if (set == null) return
        val ta: TypedArray = context.obtainStyledAttributes(set, R.styleable.SampleView)
        mSquareColor == ta.getColor(R.styleable.SampleView_square_color, Color.GREEN)
        mSquareSize =
            ta.getDimension(R.styleable.SampleView_square_size, SQUARE_SIZE_DEF.toFloat()).toInt()
        mPaintSquare.color = mSquareColor
        ta.recycle()

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    fun swapColor() {
        when (mPaintSquare.color) {
            mSquareColor -> mPaintSquare.color = Color.RED
            else -> mPaintSquare.color = mSquareColor
        }
        postInvalidate()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        mRectSquare.left = 50
        mRectSquare.top = 50
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE_DEF
        mRectSquare.bottom = mRectSquare.bottom + SQUARE_SIZE_DEF
        Log.e("state", "tekrar oluştu")

        val imageX = (width - mImage.width) / 2
        val imageY = (height - mImage.height) / 2

        //Drawing image at bitmap
        canvas?.drawBitmap(mImage, imageX.toFloat(), imageY.toFloat(), null)

        if (mCircleX == 0F || mCircleY == 0F) {
            mCircleX = (width / 2).toFloat()
            mCircleY = (height / 2).toFloat()
        }
        //Drawing circle shape
        canvas?.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaintCircle)

        // Drawing text default location

        //Drawing some text
        canvas?.drawText(
            text,
            mTextX, mTextY,
            mPaintText
        )
        canvas?.drawRect(mRectText, mPaintText)
        canvas?.drawRect(mRectSquare, mPaintSquare)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val value = super.onTouchEvent(event)
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                val x = event.x
                val y = event.y
                if (mRectSquare.left < x && mRectSquare.right > x) {
                    if (mRectSquare.top < y && mRectSquare.bottom > y) {
                        Log.e("nokta", "içerde")

                    }
                }
                return true

            }
            MotionEvent.ACTION_MOVE -> {
                val x = event.x
                val y = event.y
                val dx = Math.pow((x - mCircleX).toDouble(), 2.toDouble())
                val dy = Math.pow((y - mCircleY).toDouble(), 2.toDouble())

                if (dx + dy < Math.pow(mCircleRadius.toDouble(), 2.0)) {
                    mCircleX = x
                    mCircleY = y
                    postInvalidate()
                    return true
                }


            }
        }
        return value

    }

    private fun getResizeBitmap(bitmap: Bitmap, reqWidth: Int, reqHeight: Int): Bitmap {
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