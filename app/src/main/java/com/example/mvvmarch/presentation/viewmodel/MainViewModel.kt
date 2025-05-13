package com.example.mvvmarch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmarch.di.common.Dispatcher
import com.example.mvvmarch.domain.usecase.GetCartItemCountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dispatcher: Dispatcher,
    private val getCartItemCountUseCase: GetCartItemCountUseCase
) : ViewModel() {

    private val _cartItemCount = MutableStateFlow(0)
    val cartItemCount: StateFlow<Int> = _cartItemCount

    init {
        viewModelScope.launch(dispatcher.io) {
            _cartItemCount.value = getCartItemCountUseCase()
        }

    }
}