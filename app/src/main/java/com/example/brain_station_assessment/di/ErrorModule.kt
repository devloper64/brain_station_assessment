package com.example.brain_station_assessment.di


import com.example.brain_station_assessment.data.error.mapper.ErrorMapper
import com.example.brain_station_assessment.data.error.mapper.ErrorMapperSource
import com.example.brain_station_assessment.errors.ErrorManager
import com.example.brain_station_assessment.errors.ErrorUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// with @Module we Telling Dagger that, this is a Dagger module
@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorModule {
    @Binds
    @Singleton
    abstract fun provideErrorFactoryImpl(errorManager: ErrorManager): ErrorUseCase

    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapper): ErrorMapperSource
}
