package com.samnetworks.base.utils

import android.content.res.Resources
import android.graphics.Rect
import android.graphics.RectF
import android.util.DisplayMetrics
import kotlin.math.roundToInt

object DisplayUtils {
    private val displayMetrics: DisplayMetrics by lazy { Resources.getSystem().displayMetrics }

    val screenRectPx: Rect get() = displayMetrics.run { Rect(0, 0, widthPixels, heightPixels) }

    val screenRectDp: RectF get() = displayMetrics.run { RectF(0f, 0f, widthPixels.px2dp, heightPixels.px2dp) }

    val Number.px2dp: Float get() = this.toFloat() / displayMetrics.density

    val Number.dp2px: Int get() = (this.toFloat() * displayMetrics.density).roundToInt()

    val screenWidthPx: Int get() = Resources.getSystem().displayMetrics.widthPixels

    val screenHeightPx: Int get() = Resources.getSystem().displayMetrics.heightPixels
}