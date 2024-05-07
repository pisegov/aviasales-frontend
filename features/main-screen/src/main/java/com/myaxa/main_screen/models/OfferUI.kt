package com.myaxa.main_screen.models

import com.myaxa.common.StringFormatter
import com.myaxa.domain.models.Offer

internal data class OfferUI(
    val id: Int,
    val imageResource: Int?,
    val title: String,
    val town: String,
    val price: String,
) : ListItem

internal fun Offer.toUiModel(): OfferUI = OfferUI(
    id = id,
    imageResource = imageResource,
    title = title,
    town = town,
    price = StringFormatter.formatPrice(price),
)