package com.myaxa.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.myaxa.database.models.OfferDBO
import com.myaxa.database.models.TicketDBO
import com.myaxa.database.models.TicketsOfferDBO

@Database(
    entities = [
        OfferDBO::class,
        TicketsOfferDBO::class,
        TicketDBO::class,
    ],
    version = 1
)
@TypeConverters(LocalDateTimeConverters::class)
abstract class TicketsSearchDatabase : RoomDatabase() {
    abstract val dao: TicketsSearchDao
}

class DatabaseModule(applicationContext: Context) {
    val db = Room.databaseBuilder(
        applicationContext.applicationContext,
        TicketsSearchDatabase::class.java,
        "tickets_search",
    ).build()
}