package com.myaxa.database

import com.myaxa.database.models.OfferDBO
import com.myaxa.database.models.TicketDBO
import com.myaxa.database.models.TicketsOfferDBO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MainLocalDataSource internal constructor(private val db: TicketsSearchDatabase) {
    suspend fun insertOffers(list: List<OfferDBO>) = withContext(Dispatchers.IO) {
        db.dao.insertOffers(list)
    }

    suspend fun insertTicketsOffers(list: List<TicketsOfferDBO>) = withContext(Dispatchers.IO) {
        db.dao.insertTicketsOffers(list)
    }

    suspend fun insertTickets(list: List<TicketDBO>) = withContext(Dispatchers.IO) {
        db.dao.insertTickets(list)
    }

    fun getOffers(): Flow<List<OfferDBO>> = db.dao.getOffers()

    fun getTicketsOffers(): Flow<List<TicketsOfferDBO>> = db.dao.getTicketsOffers()

    fun getTickets(): Flow<List<TicketDBO>> = db.dao.getTickets()
}

fun MainLocalDataSource(databaseModule: DatabaseModule): MainLocalDataSource {
    return MainLocalDataSource(databaseModule.db)
}