package com.myaxa.domain.models

interface MainRepository {
    suspend fun getOffers(): List<Offer>

    suspend fun getTicketsOffers(): List<TicketsOffer>

    suspend fun getTickets(): List<Ticket>
}

interface DestinationsRepository {

    fun getSearchDestinations(): List<Destination>
}