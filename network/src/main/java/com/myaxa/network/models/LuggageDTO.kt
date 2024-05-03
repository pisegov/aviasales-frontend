package com.myaxa.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LuggageDTO(
    @SerialName("has_luggage") val hasLuggage: Boolean,
    @SerialName("price") val price: PriceDTO,
)

@Serializable
data class HandLuggageDTO(
    @SerialName("has_hand_luggage") val hasHandLuggage: Boolean,
    @SerialName("size") val size: String,
)