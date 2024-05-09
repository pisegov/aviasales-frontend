package com.myaxa.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.myaxa.database.models.OfferDBO
import com.myaxa.database.models.TicketDBO
import com.myaxa.database.models.TicketsOfferDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketsSearchDao {

    @Upsert
    suspend fun insertOffers(list: List<OfferDBO>)

    @Upsert
    suspend fun insertTicketsOffers(list: List<TicketsOfferDBO>)

    @Upsert
    suspend fun insertTickets(list: List<TicketDBO>)

    @Query("select * from offers")
    fun getOffers(): Flow<List<OfferDBO>>

    @Query("select * from tickets_offers")
    fun getTicketsOffers(): Flow<List<TicketsOfferDBO>>

    @Query("select * from tickets")
    fun getTickets(): Flow<List<TicketDBO>>
}