package com.myaxa.network

import com.myaxa.network.models.OffersResponseDTO
import com.myaxa.network.models.TicketsOffersResponseDTO
import com.myaxa.network.models.TicketsResponseDTO
import retrofit2.http.GET

internal interface Api {

    @GET("offers")
    suspend fun getOffers() : Result<OffersResponseDTO>

    @GET("tickets_offers")
    suspend fun getTicketsOffers() : Result<TicketsOffersResponseDTO>

    @GET("tickets")
    suspend fun getTickets() : Result<TicketsResponseDTO>
}