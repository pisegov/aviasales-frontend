package com.myaxa.search_selected_country_impl.models

import androidx.annotation.ColorRes
import com.myaxa.common.StringFormatter
import com.myaxa.domain.models.TicketsOffer

internal data class TicketsOfferUI(
    val title: String,
    val timeRange: String,
    val price: String,
    @ColorRes val badgeColor: Int,
) : ListItem

internal fun TicketsOffer.toUiModel(): TicketsOfferUI {
    val badgeColor = when(id) {
        1 -> com.myaxa.common.R.color.red
        10 -> com.myaxa.common.R.color.blue
        else -> com.myaxa.common.R.color.white
    }

    return TicketsOfferUI(
        title = title,
        timeRange = timeRange.joinToString(separator = "  "),
        price = StringFormatter.formatPrice(price),
        badgeColor = badgeColor,
    )
}
