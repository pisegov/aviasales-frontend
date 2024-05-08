package com.myaxa.common

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object StringFormatter {

    fun formatPrice(price: Int): String {
        return DecimalFormat("###,###", DecimalFormatSymbols().apply {
            groupingSeparator = ' '
        }).format(price)
    }

    fun formatTime(date: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
            .withZone(ZoneId.systemDefault())
        return formatter.format(date)
    }

    fun formatDate(date: LocalDate): String {

        val formatter = DateTimeFormatter.ofPattern("d MMM", Locale("ru"))

        return date.format(formatter)
    }

    fun formatDateWithWeekDay(date: LocalDate): String {

        val formatter = DateTimeFormatter.ofPattern("d MMM, EEE", Locale("ru"))

        return date.format(formatter)
    }
}