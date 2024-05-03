package com.myaxa.domain.models

interface Repository {
    suspend fun getOffers(): List<Offer>

    suspend fun getTicketsOffers(): List<TicketsOffer>

    suspend fun getTickets(): List<Ticket>
}