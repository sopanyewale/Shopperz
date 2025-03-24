package com.example.mvvmarch.di.module

import com.example.mvvmarch.data.repository.ProductRepositoryImpl
import com.example.mvvmarch.domain.usecase.GetProductCategoryUseCase
import com.example.mvvmarch.domain.usecase.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetProductsUseCase(repository: ProductRepositoryImpl) = GetProductsUseCase(repository)

    @Provides
    @Singleton
    fun provideGetProductCategoryUseCase(repository: ProductRepositoryImpl) =
        GetProductCategoryUseCase(repository)
}