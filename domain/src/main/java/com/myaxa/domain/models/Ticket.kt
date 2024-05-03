package com.myaxa.domain.models

data class Ticket(
    val id: Int,
    val badge: String?,
    val price: Int,
    val providerName: String,
    val company: String,
    val departure: Transit,
    val arrival: Transit,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val hasLuggage: Boolean,
    val luggagePrice: Int,
    val hasHandLuggage: Boolean,
    val handLuggageSize: String,
    val isReturnable: Boolean,
    val isExchangeable: Boolean,
)