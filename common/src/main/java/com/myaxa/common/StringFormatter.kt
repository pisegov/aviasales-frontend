package com.myaxa.common

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object StringFormatter {

    fun formatPrice(price: Int): String {
        return DecimalFormat("###,###", DecimalFormatSymbols().apply {
            groupingSeparator = ' '
        }).format(price)
    }

    fun formatDate(date: LocalDate): String {

        val formatter = DateTimeFormatter.ofPattern("d MMM, EEE", Locale("ru"))

        return date.format(formatter)
    }
}