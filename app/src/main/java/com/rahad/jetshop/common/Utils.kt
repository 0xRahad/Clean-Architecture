package com.rahad.jetshop.common

import com.rahad.jetshop.data.model.HitDTO
import com.rahad.jetshop.domain.model.ImageModel

fun HitDTO.toDomainModel() : ImageModel {
    return ImageModel(
        imageUrl = largeImageURL
    )
}