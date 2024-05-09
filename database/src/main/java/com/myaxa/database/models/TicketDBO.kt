package com.myaxa.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "tickets")
data class TicketDBO(
    @ColumnInfo("id") @PrimaryKey val id: Int,
    @ColumnInfo("badge") val badge: String?,
    @ColumnInfo("price") val price: Int,
    @ColumnInfo("provider_name") val providerName: String,
    @ColumnInfo("company") val company: String,
    @ColumnInfo("has_transfer") val hasTransfer: Boolean,
    @ColumnInfo("departure_town") val departureTown: String,
    @ColumnInfo("departure_airport") val departureAirport: String,
    @ColumnInfo("departure_date") val departureDate: LocalDateTime,
    @ColumnInfo("arrival_town") val arrivalTown: String,
    @ColumnInfo("arrival_airport") val arrivalAirport: String,
    @ColumnInfo("arrival_date") val arrivalDate: LocalDateTime,
)