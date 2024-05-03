package com.myaxa.network.models

import com.myaxa.domain.models.Transit
import com.myaxa.network.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Serializable
data class TransitDTO(
    @SerialName("town") val town: String,
    @SerialName("airport") val airport: String,
    @SerialName("date") @Serializable(with = DateSerializer::class) val date: Instant,
) {
    fun toTransit(): Transit {
        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
            .withZone(ZoneId.systemDefault())

        return Transit(town = town, airport = airport, date = formatter.format(date))
    }
}