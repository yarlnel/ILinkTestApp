package com.example.ilinktestapp.listeners

import android.view.View

class OnDoubleClickListener
    (private val callback: (View?) -> Unit)
    : View.OnClickListener {

    var lastClickTime: Long = 0
    override fun onClick(v: View?) {
        val clickTime = System.currentTimeMillis()
        if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
            callback(v)
        }
        lastClickTime = clickTime
    }

    companion object {
        private const val DOUBLE_CLICK_TIME_DELTA: Long = 300 //milliseconds
    }
}