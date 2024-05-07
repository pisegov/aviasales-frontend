package com.myaxa.domain.models

interface MainRepository {
    suspend fun getOffers(): List<Offer>

    suspend fun getTickets(): List<Ticket>
}

interface DirectFlightOffersRepository {

    suspend fun getTicketsOffers(): List<TicketsOffer>
}

interface DestinationsRepository {

    fun getSearchDestinations(): List<Destination>
}