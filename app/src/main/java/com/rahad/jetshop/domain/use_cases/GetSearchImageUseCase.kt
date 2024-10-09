package com.rahad.jetshop.domain.use_cases

import com.rahad.jetshop.common.Resource
import com.rahad.jetshop.domain.model.ImageModel
import com.rahad.jetshop.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSearchImageUseCase(
    private val repository: ImageRepository
) {

    operator fun invoke(query: String) : Flow<Resource<List<ImageModel>>> = flow {
        try {
            emit(Resource.Loading())
            val images = repository.getSearchImage(query)
            emit(Resource.Success(images))
        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}