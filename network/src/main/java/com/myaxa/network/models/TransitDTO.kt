package com.myaxa.network.models

import com.myaxa.domain.models.Transit
import com.myaxa.network.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class TransitDTO(
    @SerialName("town") val town: String,
    @SerialName("airport") val airport: String,
    @SerialName("date") @Serializable(with = DateSerializer::class) val date: LocalDateTime,
) {
    fun toTransit(): Transit = Transit(town = town, airport = airport, date = date)
}