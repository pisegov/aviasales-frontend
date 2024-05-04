package com.myaxa.main_screen.models

import com.myaxa.domain.models.Offer
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

internal data class OfferUI(
    val id: Int,
    val imageResource: Int?,
    val title: String,
    val town: String,
    val price: String,
) : ListItem

internal fun Offer.toUiModel(): OfferUI {

    val priceStr = DecimalFormat("###,###", DecimalFormatSymbols().apply {
        groupingSeparator = ' '
    }).format(price)

    return OfferUI(
        id = id,
        imageResource = imageResource,
        title = title,
        town = town,
        price = priceStr,
    )
}