package com.myaxa.network

import com.myaxa.network.models.OffersResponseDTO
import com.myaxa.network.models.TicketsOffersResponseDTO
import com.myaxa.network.models.TicketsResponseDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.create

class RemoteDataSource internal constructor(private val api: Api) {

    suspend fun getOffers(): Result<OffersResponseDTO> = withContext(Dispatchers.IO) {
        api.getOffers()
    }

    suspend fun getTicketsOffers(): Result<TicketsOffersResponseDTO> = withContext(Dispatchers.IO) {
        api.getTicketsOffers()
    }

    suspend fun getTickets(): Result<TicketsResponseDTO> = withContext(Dispatchers.IO) {
        api.getTickets()
    }
}

fun RemoteDataSource(retrofitModule: RetrofitModule): RemoteDataSource {

    val api: Api = retrofitModule.retrofit.create()
    return RemoteDataSource(api)
}
