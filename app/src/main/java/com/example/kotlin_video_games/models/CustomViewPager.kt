package com.example.kotlin_video_games.models

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import androidx.viewpager.widget.ViewPager

// this class copied from github for make clickable viewpager
// https://gist.github.com/pawel-schmidt/456b4525c04b64403eb266edb00874c9
class CustomViewPager @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null) :
    ViewPager(
        context!!, attrs
    ) {
    private var onClickListener: OnClickListener? = null
    override fun setOnClickListener(onClickListener: OnClickListener?) {
        this.onClickListener = onClickListener
    }

    private inner class OnSingleTapConfirmedGestureListener(private val view: View) :
        SimpleOnGestureListener() {
        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            if (onClickListener != null) {
                onClickListener?.onClick(view)
            }
            return true
        }
    }

    init {
        val onSingleTapConfirmedGestureDetector =
            GestureDetector(context, OnSingleTapConfirmedGestureListener(this))
        setOnTouchListener { v, event ->
            onSingleTapConfirmedGestureDetector.onTouchEvent(event)
            false
        }
    }
}