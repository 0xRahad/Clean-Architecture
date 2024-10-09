package com.rahad.jetshop.data.network

import com.rahad.jetshop.data.model.PixaBayDTO
import com.rahad.jetshop.common.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaBayAPI {

    @GET("api/")
    suspend fun getSearchImage(
        @Query("key") key:String = Constants.API_KEY,
        @Query("q") searchQuery:String,
        @Query("image_type") imageType:String = "photo",
        @Query("pretty") pretty:Boolean = true
    ) : PixaBayDTO


}

