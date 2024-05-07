package com.myaxa.common

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import jakarta.inject.Inject

class StringPainter @Inject constructor(private val applicationContext: Context) {

    fun paintSubstring(string: String, start: Int, end: Int, @ColorRes colorRes: Int): SpannableString {
        val spannable = SpannableString(string)

        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(applicationContext, colorRes)),
            start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return spannable
    }
}