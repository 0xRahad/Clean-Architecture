package com.rahad.jetshop.data.repository

import com.rahad.jetshop.common.toDomainModel
import com.rahad.jetshop.data.network.PixaBayAPI
import com.rahad.jetshop.domain.model.ImageModel
import com.rahad.jetshop.domain.repository.ImageRepository

class ImageRepoImpl (
    private val api: PixaBayAPI
) : ImageRepository {

    override suspend fun getSearchImage(query: String): List<ImageModel> {
        try {
            return api.getSearchImage(searchQuery = query).hits.map {
                it.toDomainModel()
            }
        }catch (e:Exception){
            throw Exception(e)
        }
    }
}