package com.myaxa.network.models

import com.myaxa.domain.models.TicketsOffer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketsOfferDTO(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("time_range") val timeRange: List<String>,
    @SerialName("price") val price: PriceDTO,
) {
    fun toTicketsOffer(): TicketsOffer {
       return TicketsOffer(
           id = id,
           title = title,
           timeRange = timeRange,
           price = price.value
       )
    }
}
