package com.myaxa.domain.models

import java.time.LocalDateTime

data class Transit(
    val town: String,
    val airport: String,
    val date: LocalDateTime,
)