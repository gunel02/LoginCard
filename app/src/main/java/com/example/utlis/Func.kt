package com.example.utlis

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.logincard.R
import com.google.android.material.card.MaterialCardView
import kotlin.math.roundToInt

object Func {
    private var globalToast: Toast? = null

    fun setMargins(
        context: Context?, view: ViewGroup?, leftDp: Int, topDp: Int, rightDp: Int, bottomDp: Int
    ) {
        try {
            if (view == null || context == null) return
            if (view.layoutParams is ViewGroup.MarginLayoutParams) {
                val p = view.layoutParams as ViewGroup.MarginLayoutParams
                p.setMargins(
                    dpToPx(context, leftDp),
                    dpToPx(context, topDp),
                    dpToPx(context, rightDp),
                    dpToPx(context, bottomDp)
                )
                view.requestLayout()
            }
        } catch (e: Exception) {
            Log.e("error", "setMargins: " + e.message)
        }
    }

    fun dpToPx(context: Context?, dp: Int): Int {
        if (context == null) return 0
        val density = context.resources.displayMetrics.density
        return (dp.toFloat() * density).roundToInt()
    }

    fun pxToDp(context: Context?, px: Int): Int {
        return if (context == null) 0 else (px / context.resources.displayMetrics.density).toInt()
    }

    fun dpToPx(context: Context?, dp: Float): Float {
        return if (context == null) 0f else dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun pxToDp(context: Context?, px: Float): Float {
        return if (context == null) 0f else px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun getTimerTextByMilliseconds(milliseconds: Long): String {
        var seconds = milliseconds / 1000
        var minutes = 0L
        if (seconds > 60) {
            minutes = seconds / 60L
        }

        seconds -= minutes * 60

        var minutesString = minutes.toString()
        var secondsString = seconds.toString()

        if (minutesString.length == 1) {
            minutesString = "0$minutesString"
        }

        if (secondsString.length == 1) {
            secondsString = "0$secondsString"
        }

        return "$minutesString:$secondsString"
    }

    fun vibrator(context: Context?, durationMillis: Long) {
        if (context == null) return
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        // Check if the device has vibration capabilities
        if (vibrator.hasVibrator()) {
            // Vibrate for the specified duration
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                // For Android 8.0 (API level 26) and above
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        durationMillis, VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            } else {
                // For devices running below Android 8.0
                vibrator.vibrate(durationMillis)
            }
        }
    }

    fun showToast(context: Context?, stringRes: String?) {
        try {
            if (context == null || stringRes == null) return
            globalToast?.cancel()

            val layoutInflater = LayoutInflater.from(context)
            val view = layoutInflater.inflate(R.layout.custom_toast, null)

            val textViewMessage: TextView = view.findViewById(R.id.textViewMessage)
            val cardView: MaterialCardView = view.findViewById(R.id.card_view)
            textViewMessage.text = stringRes
            cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.neutral_100))
            val toast = Toast(context)
            toast.setGravity(Gravity.BOTTOM, 0, dpToPx(context, 60))
            toast.duration = Toast.LENGTH_SHORT
            toast.view = view
            toast.show()
            globalToast = toast
        } catch (e: Exception) {
            Log.d("TAG_try_error", "showToast: " + e.message)
        }
    }


}