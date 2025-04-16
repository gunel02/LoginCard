package com.example.logincard.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class RippleFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    init {
        isClickable = true
        isFocusable = true
    }

    override fun setPressed(pressed: Boolean) {
        super.setPressed(pressed)
        if (pressed) {
            animate().alpha(0.5f).setDuration(150).start()
        } else {
            animate().alpha(1f).setDuration(150).start()
        }
    }
}