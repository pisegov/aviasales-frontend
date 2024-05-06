package com.myaxa.local

import jakarta.inject.Inject

class ImagesLocalDataSource @Inject constructor() {
    private val offersImages = mapOf(
        1 to R.drawable.list_item_1,
        2 to R.drawable.list_item_2,
        3 to R.drawable.list_item_3,
    )

    fun getOfferImageById(id: Int): Int? = offersImages[id]
}