package com.aefremov.rtngbr

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat

class SimpleRatingBar  : LinearLayout, BaseRatingBar {

    val TAG = SimpleRatingBar::class.java.name

    private var currentRating = 0f
    private var previousRating = 0f

    private var numStars = 0f
    private var padding = 16
    private var starWidth = 0
    private var startHeight = 0
    private var minimumStars = 0f

    private var mIsClickable = true
    private var isClearRatingEnabled = true

    private var startX = 0f
    private var startY = 0f

    private var emptyDrawable: Drawable? = null
    private var filledDrawable: Drawable? = null

    private var listener: RatingChangeListener? = null

    protected lateinit var starViews: MutableList<StarView>

    constructor(context: Context) : this(context, null) {
//        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
//        setAttributes(attrs)
//        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
//        setAttributes(attrs)
//        initView()
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleRatingBar)
        val rating = typedArray.getFloat(R.styleable.SimpleRatingBar_rating, 0f)

        emptyDrawable = if (typedArray.hasValue(R.styleable.SimpleRatingBar_drawableEmpty)) {
            ContextCompat.getDrawable(context, typedArray.getResourceId(R.styleable.SimpleRatingBar_drawableEmpty, View.NO_ID))
        } else {
            null
        }
        filledDrawable = if (typedArray.hasValue(R.styleable.SimpleRatingBar_drawableFilled)) {
            ContextCompat.getDrawable(context, typedArray.getResourceId(R.styleable.SimpleRatingBar_drawableFilled, View.NO_ID))
        } else {
            null
        }
        numStars = typedArray.getFloat(R.styleable.SimpleRatingBar_numStars, numStars)
        currentRating = typedArray.getFloat(R.styleable.SimpleRatingBar_rating, currentRating)
        minimumStars = typedArray.getFloat(R.styleable.SimpleRatingBar_minimumStars, minimumStars)
        starWidth = typedArray.getDimensionPixelSize(R.styleable.SimpleRatingBar_starWidth, 0)
        startHeight = typedArray.getDimensionPixelSize(R.styleable.SimpleRatingBar_starHeight, 0)

        typedArray.recycle()

//        minimumStars = 5f
//        numStars = 5f
        padding = 16
//        if (emptyDrawable == null) {
            emptyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.star_empty)
//        }
//        if (filledDrawable == null) {
            filledDrawable = ContextCompat.getDrawable(getContext(), R.drawable.star_filled)
//        }

        starViews = mutableListOf()
        for (i in 0 until numStars.toInt()) {
            val view = StarView(getContext(), i, starWidth, startHeight, padding)
            view.setEmptyDrawable(emptyDrawable)
            view.setFilledDrawable(filledDrawable)
            addView(view)

            starViews.add(view)
        }

//        setRating(rating)
    }

    private fun setAttributes(attrs: AttributeSet) {
    }

    private fun initView() {
//        LayoutInflater.from(context).inflate(R.layout.widget_rating_bar, this, true)
    }

    override fun setRating(rating: Float) {
//        currentRating = value
//        var _rating = rating

//        if (_rating > numStars) { _rating = numStars }
//        if (_rating < minimumStars) { _rating = minimumStars }

//        if (currentRating == _rating) return
//        currentRating = _rating
        currentRating = rating

        listener?.onRatingChange(currentRating)

        fillRatingBar(rating)
    }

    override fun getRating() : Float = currentRating

    override fun setStarWidth(width: Int) {
        starWidth = width
        for (i in starViews.indices) {
            starViews[i].setStarWidth(width)
        }
    }

    override fun getStarWidth(): Int = starWidth

    override fun setStarHeight(height: Int) {
        startHeight = height
        for (i in starViews.indices) {
            starViews[i].setStarHeight(height)
        }
    }

    override fun getStarHeight(): Int = startHeight

    override fun setEmptyDrawable(drawable: Drawable) {
        emptyDrawable = drawable
        for (i in starViews.indices) {
            starViews[i].setEmptyDrawable(drawable)
        }
    }

    override fun setEmptyDrawableRes(res: Int) {
        setEmptyDrawable(ContextCompat.getDrawable(context, res)!!)
    }

    override fun setFilledDrawable(drawable: Drawable) {
        filledDrawable = drawable
        for (i in starViews.indices) {
            starViews[i].setFilledDrawable(drawable)
        }
    }

    override fun setFilledDrawableRes(res: Int) {
        setFilledDrawable(ContextCompat.getDrawable(context, res)!!)
    }

    override fun setMinimumStars(minimumStars: Float) {
        this.minimumStars = getValidMinimumStars(minimumStars, numStars, 1f)
    }

    fun getValidMinimumStars(minimumStars: Float, numStars: Float, stepSize: Float) : Float {
        if (minimumStars < 0) {
            return 0f
        }
        if (minimumStars > numStars) {
            return numStars
        }
        if (minimumStars.toInt() % stepSize.toInt() != 0) {
            return stepSize
        }
        return minimumStars
    }

    protected fun fillRatingBar(rating: Float) {
        for (i in starViews.indices) {
            val starView = starViews[i]
            val ratingViewId = starView.tag as Int
            val maxIntOfRating = Math.ceil(rating.toDouble())

            if (ratingViewId > maxIntOfRating - 1) {
                starView.setEmpty()
                continue
            } else {
                starView.setFilled()
            }

//            if (ratingViewId != maxIntOfRating.toInt()) {
//                starView.setFilled()
//            }
        }

//        for (i in starViews.indices) {
//            val starView = starViews[i]
//            val ratingViewId = starView.tag as Int
//            val maxIntOfRating = Math.ceil(rating.toDouble())
//            if (ratingViewId > maxIntOfRating) {
//                starView.setEmpty()
//                continue
//            }
//            if (ratingViewId == maxIntOfRating.toInt()) {
//                starView.setFilledDrawable()
//            }
//            starView.setFilled()
//        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val eventX = event.x
        val eventY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = eventX
                startY = eventY
                previousRating = currentRating
            }
            MotionEvent.ACTION_MOVE -> {
            }
            MotionEvent.ACTION_UP -> {
                handleClickEvent(eventX)
            }
        }

        parent.requestDisallowInterceptTouchEvent(true)
        return true
    }

    fun handleClickEvent(eventX: Float) {
        for (i in starViews.indices) {
            if (!isPositionInRatingView(eventX, starViews[i])) continue
            val rating = starViews[i].tag as Int
            if (previousRating == rating.toFloat()) {
                setRating(minimumStars)
            } else {
                setRating(rating.toFloat())
            }
        }
    }

    private fun isPositionInRatingView(eventX: Float, ratingView: View): Boolean {
        return eventX > ratingView.left && eventX < ratingView.right
    }

    fun setOnRatingChangeListener(listener: RatingChangeListener) {
        this.listener = listener
    }

    interface RatingChangeListener {
        fun onRatingChange(rating: Float)
    }
}