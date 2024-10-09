package com.rahad.jetshop.data.model

data class PixaBayDTO(
    val total: Int,
    val totalHits: Int,
    val hits: List<HitDTO>
)