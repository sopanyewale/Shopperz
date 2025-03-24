package com.example.mvvmarch.di.module

import com.example.mvvmarch.data.remote.ApiService
import com.example.mvvmarch.data.repository.ProductRepositoryImpl
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
    fun provideProductRepository(apiService : ApiService) = ProductRepositoryImpl(apiService)

}

