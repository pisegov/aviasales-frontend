package com.myaxa.tickets_api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class TicketsScreenParams(
    val departure: String,
    val arrival: String,
    val departureDate: LocalDate,
) : Parcelable