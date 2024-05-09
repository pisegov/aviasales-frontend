package com.myaxa.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "offers")
data class OfferDBO(
    @ColumnInfo("offer_id") @PrimaryKey val id: Int,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("town") val town: String,
    @ColumnInfo("price") val price: Int,
)