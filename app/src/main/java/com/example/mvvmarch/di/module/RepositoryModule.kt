package com.example.mvvmarch.di.module

import com.example.mvvmarch.data.local.ProductDao
import com.example.mvvmarch.data.remote.ApiService
import com.example.mvvmarch.data.repository.CartRepositoryImpl
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

    @Provides
    @Singleton
    fun provideCartRepository(productDao: ProductDao) = CartRepositoryImpl(productDao)

}

