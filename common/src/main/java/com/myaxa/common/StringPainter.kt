package com.myaxa.common

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorInt

object StringPainter {

    fun paintSubstring(string: String, start: Int, end: Int, @ColorInt color: Int): SpannableString {
        val spannable = SpannableString(string)

        spannable.setSpan(
            ForegroundColorSpan(color),
            start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return spannable
    }
}