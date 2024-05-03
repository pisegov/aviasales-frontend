package com.myaxa.data

import com.myaxa.domain.models.Offer
import com.myaxa.domain.models.Repository
import com.myaxa.domain.models.Ticket
import com.myaxa.domain.models.TicketsOffer
import com.myaxa.network.RemoteDataSource
import jakarta.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) : Repository {
    override suspend fun getOffers(): List<Offer> {
        val responseResult = remoteDataSource.getOffers()

        return responseResult.getOrNull()?.list?.map { it.toOffer() } ?: emptyList()
    }

    override suspend fun getTicketsOffers(): List<TicketsOffer> {
        val responseResult = remoteDataSource.getTicketsOffers()

        return responseResult.getOrNull()?.list?.map { it.toTicketsOffer() } ?: emptyList()
    }

    override suspend fun getTickets(): List<Ticket> {
        val responseResult = remoteDataSource.getTickets()

        return responseResult.getOrNull()?.list?.map { it.toTicket() } ?: emptyList()
    }
}