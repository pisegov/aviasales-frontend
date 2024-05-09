package com.myaxa.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tickets_offers")
data class TicketsOfferDBO(
    @ColumnInfo("tickets_offer_id") @PrimaryKey val id: Int,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("time_range") val timeRange: String,
    @ColumnInfo("price") val price: Int,
)
