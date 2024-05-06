package com.myaxa.search_impl.models

import androidx.annotation.DrawableRes
import com.myaxa.domain.models.Destination

internal sealed interface ListItem

internal data class DestinationUI(
    @DrawableRes val imageId: Int,
    val name: String,
) : ListItem

internal fun Destination.toUIModel() = DestinationUI(
    imageId = imageResource,
    name = title,
)