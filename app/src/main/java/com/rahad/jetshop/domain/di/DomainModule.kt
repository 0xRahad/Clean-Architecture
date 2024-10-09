package com.rahad.jetshop.domain.di

import com.rahad.jetshop.domain.repository.ImageRepository
import com.rahad.jetshop.domain.use_cases.GetSearchImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideGetSearchImageUseCase(repository: ImageRepository): GetSearchImageUseCase {
        return GetSearchImageUseCase(repository)
    }
}