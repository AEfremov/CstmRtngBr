package com.aefremov.rtngbr

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.FloatRange

interface BaseRatingBar {

    fun setRating(rating: Float)

    fun getRating() : Float

    fun setStarWidth(width: Int)

    fun getStarWidth() : Int

    fun setStarHeight(height: Int)

    fun getStarHeight() : Int

    fun setEmptyDrawable(drawable: Drawable)

    fun setEmptyDrawableRes(@DrawableRes res: Int)

    fun setFilledDrawable(drawable: Drawable)

    fun setFilledDrawableRes(@DrawableRes res: Int)

    fun setMinimumStars(@FloatRange(from = 0.0) minimumStars: Float)

}
