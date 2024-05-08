package com.myaxa.domain.models

import java.time.Duration

data class Ticket(
    val id: Int,
    val badge: String?,
    val price: Int,
    val providerName: String,
    val company: String,
    val departure: Transit,
    val arrival: Transit,
    val flightDuration: Duration,
    val hasTransfer: Boolean,
)