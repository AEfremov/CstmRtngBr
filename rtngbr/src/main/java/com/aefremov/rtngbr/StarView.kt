package com.aefremov.rtngbr

import android.content.Context
import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout

class StarView : RelativeLayout {

    private lateinit var filledView: ImageView
    private lateinit var emptyView: ImageView

    private var starWidth: Int = 0
    private var starHeight: Int = 0

    constructor(context: Context?, viewId: Int, width: Int, height: Int, padding: Int) : super(context) {
        starWidth = width
        starHeight = height
        tag = viewId
        setPadding(padding, padding, padding, padding)
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs) {
//        setAttributes(attrs)
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
//        setAttributes(attrs)
        initView()
    }

    private fun initView() {
        val params: ViewGroup.LayoutParams = ViewGroup.LayoutParams(
            if (starWidth == 0) RelativeLayout.LayoutParams.WRAP_CONTENT else starWidth,
            if (starHeight == 0) RelativeLayout.LayoutParams.WRAP_CONTENT else starHeight)

        filledView = ImageView(context)
        filledView.scaleType = ImageView.ScaleType.CENTER_CROP
        addView(filledView, params)

        emptyView = ImageView(context)
        emptyView.scaleType = ImageView.ScaleType.CENTER_CROP
        addView(emptyView, params)

        setEmpty()
    }

    fun setFilledDrawable(drawable: Drawable?) {
        if (drawable == null) return
        if (drawable.constantState == null) return

        val clipDrawable = ClipDrawable(drawable.constantState!!.newDrawable(), Gravity.START, ClipDrawable.HORIZONTAL)
        filledView.setImageDrawable(clipDrawable)
    }

    fun setEmptyDrawable(drawable: Drawable?) {
        if (drawable == null) return
        if (drawable.constantState == null) return

        val clipDrawable = ClipDrawable(drawable.constantState!!.newDrawable(), Gravity.END, ClipDrawable.HORIZONTAL)
        emptyView.setImageDrawable(clipDrawable)
    }

    fun setFilled() {
        filledView.setImageLevel(10000)
        emptyView.setImageLevel(0)
    }

    fun setEmpty() {
        filledView.setImageLevel(0)
        emptyView.setImageLevel(10000)
    }

    fun setStarWidth(width: Int) {
        starWidth = width
        val params: ViewGroup.LayoutParams = filledView.layoutParams
        params.width = starWidth
        filledView.layoutParams = params
        emptyView.layoutParams = params
    }

    fun setStarHeight(height: Int) {
        starHeight = height
        val params: ViewGroup.LayoutParams = emptyView.layoutParams
        params.height = starHeight
        filledView.layoutParams = params
        emptyView.layoutParams = params
    }
}