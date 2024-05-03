package com.myaxa.network.models

import com.myaxa.domain.models.Ticket
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketDTO(
    @SerialName("value") val id: Int,
    @SerialName("badge") val badge: String?,
    @SerialName("price") val price: Int,
    @SerialName("provider_name") val providerName: String,
    @SerialName("company") val company: String,
    @SerialName("departure") val departure: TransitDTO,
    @SerialName("arrival") val arrival: TransitDTO,
    @SerialName("has_transfer") val hasTransfer: Boolean,
    @SerialName("has_visa_transfer") val hasVisaTransfer: Boolean,
    @SerialName("luggage") val luggage: LuggageDTO,
    @SerialName("hand_luggage") val handLuggage: HandLuggageDTO,
    @SerialName("is_returnable") val isReturnable: Boolean,
    @SerialName("is_exchangable") val isExchangeable: Boolean,
) {
    fun toTicket(): Ticket {
        return Ticket(
            id = id,
            badge = badge,
            price = price,
            providerName = providerName,
            company = company,
            departure = departure.toTransit(),
            arrival = arrival.toTransit(),
            hasTransfer = hasTransfer,
            hasVisaTransfer = hasVisaTransfer,
            hasLuggage = luggage.hasLuggage,
            luggagePrice = luggage.price.value,
            hasHandLuggage = handLuggage.hasHandLuggage,
            handLuggageSize = handLuggage.size,
            isReturnable = isReturnable,
            isExchangeable = isExchangeable,
        )
    }
}

