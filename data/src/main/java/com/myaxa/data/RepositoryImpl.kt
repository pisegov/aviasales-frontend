package com.myaxa.data

import com.myaxa.domain.models.Destination
import com.myaxa.domain.models.DestinationsRepository
import com.myaxa.domain.models.DirectFlightOffersRepository
import com.myaxa.domain.models.Offer
import com.myaxa.domain.models.MainRepository
import com.myaxa.domain.models.Ticket
import com.myaxa.domain.models.TicketsOffer
import com.myaxa.local.DestinationsLocalDataSource
import com.myaxa.local.ImagesLocalDataSource
import com.myaxa.network.RemoteDataSource
import jakarta.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val imagesLocalDataSource: ImagesLocalDataSource,
    private val destinationsLocalDataSource: DestinationsLocalDataSource,
) : MainRepository, DestinationsRepository, DirectFlightOffersRepository {
    override suspend fun getOffers(): List<Offer> {
        val responseResult = remoteDataSource.getOffers()

        val list = responseResult.getOrNull()?.list?.map {
            val image = imagesLocalDataSource.getOfferImageById(it.id)
            it.toOffer(imageResource = image)
        }

        return list ?: emptyList()
    }

    override suspend fun getTicketsOffers(): List<TicketsOffer> {
        val responseResult = remoteDataSource.getTicketsOffers()

        return responseResult.getOrNull()?.list?.map { it.toTicketsOffer() } ?: emptyList()
    }

    override suspend fun getTickets(): List<Ticket> {
        val responseResult = remoteDataSource.getTickets()

        return responseResult.getOrNull()?.list?.map { it.toTicket() } ?: emptyList()
    }

    override fun getSearchDestinations(): List<Destination> = destinationsLocalDataSource.getDestinations()
}