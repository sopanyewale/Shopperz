package com.example.mvvmarch.di.module

import com.example.mvvmarch.data.repository.FirebaseRepositoryImpl
import com.example.mvvmarch.domain.repository.FirebaseRepository
import com.example.mvvmarch.domain.usecase.GetFCMTokenUseCase
import com.example.mvvmarch.domain.usecase.SubscribeToTopicUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun providesFirebaseRepository() = FirebaseRepositoryImpl()

    @Provides
    @Singleton
    fun providesGetFCMTokenUseCase(repository: FirebaseRepository) = GetFCMTokenUseCase(repository)

    @Provides
    @Singleton
    fun providesSubscribeToTopicUseCase(repository: FirebaseRepository) = SubscribeToTopicUseCase(repository)

}