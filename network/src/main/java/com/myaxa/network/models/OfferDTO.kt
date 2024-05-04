package com.myaxa.network.models

import com.myaxa.domain.models.Offer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OfferDTO(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("town") val town: String,
    @SerialName("price") val price: PriceDTO,
) {
    fun toOffer(imageResource: Int? = null): Offer {
        return Offer(
            id = id,
            imageResource = imageResource,
            title = title,
            town = town,
            price = price.value
        )
    }
}

