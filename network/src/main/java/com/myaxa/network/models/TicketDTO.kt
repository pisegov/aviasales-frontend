package com.myaxa.network.models

import com.myaxa.domain.models.Ticket
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Duration

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
) {
    fun toTicket(): Ticket = Ticket(
        id = id,
        badge = badge,
        price = price.value,
        providerName = providerName,
        company = company,
        departure = departure.toTransit(),
        arrival = arrival.toTransit(),
        flightDuration = Duration.between(departure.date, arrival.date),
        hasTransfer = hasTransfer,
    )
}

