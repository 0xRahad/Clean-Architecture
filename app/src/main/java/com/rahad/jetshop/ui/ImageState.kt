package com.rahad.jetshop.ui

import com.rahad.jetshop.domain.model.ImageModel

data class ImageState(
    val isLoading: Boolean = false,
    val data : List<ImageModel>? = null,
    val error : String = ""
)