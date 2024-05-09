package com.myaxa.data

import com.myaxa.database.models.OfferDBO
import com.myaxa.database.models.TicketDBO
import com.myaxa.database.models.TicketsOfferDBO
import com.myaxa.domain.models.Offer
import com.myaxa.domain.models.Ticket
import com.myaxa.domain.models.TicketsOffer
import com.myaxa.domain.models.Transit
import com.myaxa.network.models.OfferDTO
import com.myaxa.network.models.TicketDTO
import com.myaxa.network.models.TicketsOfferDTO
import java.time.Duration

fun OfferDTO.toDBO(): OfferDBO {
    return OfferDBO(
        id = id,
        title = title,
        town = town,
        price = price.value,
    )
}

fun TicketsOfferDTO.toDBO(): TicketsOfferDBO {
    return TicketsOfferDBO(
        id = id,
        title = title,
        timeRange = timeRange.joinToString(separator = "  "),
        price = price.value
    )
}

fun TicketDTO.toDBO(): TicketDBO {
    return TicketDBO(
        id = id,
        badge = badge,
        price = price.value,
        providerName = providerName,
        company = company,
        hasTransfer = hasTransfer,
        departureAirport = departure.airport,
        departureTown = departure.town,
        departureDate = departure.date,
        arrivalAirport = arrival.airport,
        arrivalTown = arrival.town,
        arrivalDate = arrival.date,
    )
}

fun OfferDBO.toDomain(imageResource: Int? = null): Offer {
    return Offer(
        id = id,
        title = title,
        town = town,
        imageResource = imageResource,
        price = price,
    )
}

fun TicketsOfferDBO.toDomain(): TicketsOffer {
    return TicketsOffer(
        id = id,
        title = title,
        timeRange = timeRange,
        price = price
    )
}

fun TicketDBO.toDomain(): Ticket {
    return Ticket(
        id = id,
        badge = badge,
        price = price,
        providerName = providerName,
        company = company,
        hasTransfer = hasTransfer,
        departure = Transit(
            town = departureTown,
            airport = departureAirport,
            date = departureDate
        ),
        arrival = Transit(
            town = arrivalTown,
            airport = arrivalAirport,
            date = arrivalDate
        ),
        flightDuration = Duration.between(departureDate, arrivalDate)
    )
}