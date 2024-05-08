package com.myaxa.domain.models

interface OffersRepository {
    suspend fun getOffers(): List<Offer>
}

interface DirectFlightOffersRepository {

    suspend fun getTicketsOffers(): List<TicketsOffer>
}

interface DestinationsRepository {

    fun getSearchDestinations(): List<Destination>
}

interface TicketsRepository {

    suspend fun getTickets(): List<Ticket>
}
