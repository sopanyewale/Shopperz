package com.example.mvvmarch.di.module

import com.example.mvvmarch.data.repository.CartRepositoryImpl
import com.example.mvvmarch.data.repository.FirebaseRepositoryImpl
import com.example.mvvmarch.domain.repository.CartRepository
import com.example.mvvmarch.domain.repository.FirebaseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindFirebaseRepository(repository: FirebaseRepositoryImpl): FirebaseRepository

    @Binds
    @Singleton
    abstract fun bindCartRepository(repository: CartRepositoryImpl): CartRepository
}