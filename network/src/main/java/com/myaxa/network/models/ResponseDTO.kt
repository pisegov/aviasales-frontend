package com.myaxa.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OffersResponseDTO (
    @SerialName("offers") val list: List<OfferDTO>,
)

@Serializable
data class TicketsOffersResponseDTO (
    @SerialName("tickets_offers") val list: List<TicketsOfferDTO>,
)

@Serializable
data class TicketsResponseDTO (
    @SerialName("tickets") val list: List<TicketDTO>,
)

