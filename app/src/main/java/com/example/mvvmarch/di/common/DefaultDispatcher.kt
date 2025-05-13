package com.example.mvvmarch.di.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultDispatcher: Dispatcher {
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
}