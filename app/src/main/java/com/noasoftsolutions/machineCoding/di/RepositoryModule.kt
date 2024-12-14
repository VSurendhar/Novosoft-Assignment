package com.noasoftsolutions.machineCoding.di

import com.noasoftsolutions.machineCoding.api.Api
import com.noasoftsolutions.machineCoding.repository.Repository
import com.noasoftsolutions.machineCoding.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(api: Api): Repository {
        return RepositoryImpl(api)
    }

}