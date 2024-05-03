package com.myaxa.domain.models

data class TicketsOffer(
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: Int,
)
