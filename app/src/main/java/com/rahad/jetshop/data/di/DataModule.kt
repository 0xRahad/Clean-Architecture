package com.rahad.jetshop.data.di

import com.rahad.jetshop.data.network.PixaBayAPI
import com.rahad.jetshop.data.repository.ImageRepoImpl
import com.rahad.jetshop.common.Constants.BASE_URL
import com.rahad.jetshop.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesPixaBayAPI(retrofit: Retrofit): PixaBayAPI {
        return retrofit.create(PixaBayAPI::class.java)
    }

    @Provides
    fun provideImageRepo(api: PixaBayAPI): ImageRepository{
        return ImageRepoImpl(api)
    }
}