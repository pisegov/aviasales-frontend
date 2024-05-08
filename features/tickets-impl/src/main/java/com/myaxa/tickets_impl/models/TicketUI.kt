package com.myaxa.tickets_impl.models

import com.myaxa.common.StringFormatter
import com.myaxa.domain.models.Ticket

data class TicketUI(
    val badge: String?,
    val price: String,
    val departureTime: String,
    val departureAirport: String,
    val arrivalTime: String,
    val arrivalAirport: String,
    val flightDuration: String,
    val hasTransfer: Boolean,
) : ListItem

internal fun Ticket.toUiModel(): TicketUI {

    val minutes = flightDuration.toMinutes() % 60
    val hours = flightDuration.toHours().toDouble() + when {
        (minutes < 15L) -> 0.0
        (minutes > 44L) -> 1.0
        else -> 0.5
    }

    return TicketUI(
        badge = badge,
        price = StringFormatter.formatPrice(price),
        departureTime = StringFormatter.formatTime(departure.date),
        departureAirport = departure.airport,
        arrivalTime = StringFormatter.formatTime(arrival.date),
        arrivalAirport = arrival.airport,
        flightDuration = hours.toString().removeSuffix(".0"),
        hasTransfer = hasTransfer
    )
}