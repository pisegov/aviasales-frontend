package com.myaxa.domain.models

import kotlinx.coroutines.flow.Flow

interface OffersRepository {
    val offersFlow : Flow<List<Offer>>
    suspend fun loadOffers()
}

interface DirectFlightOffersRepository {

    val ticketsOffersFlow : Flow<List<TicketsOffer>>
    suspend fun loadTicketsOffers()
}

interface DestinationsRepository {

    fun getSearchDestinations(): List<Destination>
}

interface TicketsRepository {

    val ticketsFlow: Flow<List<Ticket>>
    suspend fun loadTickets()
}
