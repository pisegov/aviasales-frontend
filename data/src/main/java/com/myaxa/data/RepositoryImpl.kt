package com.myaxa.data

import com.myaxa.database.MainLocalDataSource
import com.myaxa.domain.models.Destination
import com.myaxa.domain.models.DestinationsRepository
import com.myaxa.domain.models.DirectFlightOffersRepository
import com.myaxa.domain.models.Offer
import com.myaxa.domain.models.OffersRepository
import com.myaxa.domain.models.Ticket
import com.myaxa.domain.models.TicketsOffer
import com.myaxa.domain.models.TicketsRepository
import com.myaxa.local.DestinationsLocalDataSource
import com.myaxa.local.ImagesLocalDataSource
import com.myaxa.network.RemoteDataSource
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val mainLocalDataSource: MainLocalDataSource,
    private val imagesLocalDataSource: ImagesLocalDataSource,
    private val destinationsLocalDataSource: DestinationsLocalDataSource,
) : OffersRepository, DestinationsRepository, DirectFlightOffersRepository, TicketsRepository {

    override val offersFlow: Flow<List<Offer>> = mainLocalDataSource.getOffers().map { list ->
        list.map {
            val image = imagesLocalDataSource.getOfferImageById(it.id)
            it.toDomain(imageResource = image)
        }
    }

    override val ticketsOffersFlow: Flow<List<TicketsOffer>> = mainLocalDataSource.getTicketsOffers().map { list ->
        list.map {
            it.toDomain()
        }
    }

    override val ticketsFlow: Flow<List<Ticket>> = mainLocalDataSource.getTickets().map { list ->
        list.map {
            it.toDomain()
        }
    }

    override suspend fun loadOffers() {
        val responseResult = remoteDataSource.getOffers()
        val list = responseResult.getOrNull()?.list ?: emptyList()

        mainLocalDataSource.insertOffers(list.map { it.toDBO() })
    }

    override suspend fun loadTicketsOffers() {
        val responseResult = remoteDataSource.getTicketsOffers()
        val list = responseResult.getOrNull()?.list?.map { it.toDBO() } ?: emptyList()

        mainLocalDataSource.insertTicketsOffers(list)
    }

    override suspend fun loadTickets() {
        val responseResult = remoteDataSource.getTickets()
        val list = responseResult.getOrNull()?.list?.map { it.toDBO() } ?: emptyList()

        mainLocalDataSource.insertTickets(list)
    }

    override fun getSearchDestinations(): List<Destination> = destinationsLocalDataSource.getDestinations()
}