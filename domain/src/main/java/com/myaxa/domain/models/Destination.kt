package com.myaxa.domain.models

import androidx.annotation.DrawableRes

data class Destination (
    val id: Int,
    @DrawableRes val imageResource: Int,
    val title: String,
)