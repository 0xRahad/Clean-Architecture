package com.rahad.jetshop.domain.repository

import com.rahad.jetshop.domain.model.ImageModel

interface ImageRepository {
    suspend fun getSearchImage(query: String): List<ImageModel>
}