package com.myaxa.domain.models

data class Offer (
    val id: Int,
    val imageResource: Int?,
    val title: String,
    val town: String,
    val price: Int,
)