package com.myaxa.local

import com.myaxa.domain.models.Destination
import jakarta.inject.Inject

class DestinationsLocalDataSource @Inject constructor() {
    private val destinations = listOf(
        Destination(1, R.drawable.istanbul, "Стамбул"),
        Destination(2, R.drawable.sochi, "Сочи"),
        Destination(3, R.drawable.phuket, "Пхукет"),
    )

    fun getDestinations(): List<Destination> = destinations
}