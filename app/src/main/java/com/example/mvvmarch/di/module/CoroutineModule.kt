package com.example.mvvmarch.di.module

import com.example.mvvmarch.di.common.DefaultDispatcher
import com.example.mvvmarch.di.common.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @Provides
    fun provideDispatcher(): Dispatcher = DefaultDispatcher()
}