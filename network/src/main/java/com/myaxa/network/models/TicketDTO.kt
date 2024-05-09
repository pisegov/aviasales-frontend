package com.myaxa.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketDTO(
    @SerialName("id") val id: Int,
    @SerialName("badge") val badge: String? = null,
    @SerialName("price") val price: PriceDTO,
    @SerialName("provider_name") val providerName: String,
    @SerialName("company") val company: String,
    @SerialName("departure") val departure: TransitDTO,
    @SerialName("arrival") val arrival: TransitDTO,
    @SerialName("has_transfer") val hasTransfer: Boolean,
)
