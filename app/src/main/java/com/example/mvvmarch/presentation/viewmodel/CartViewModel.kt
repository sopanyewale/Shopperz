package com.example.mvvmarch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmarch.domain.model.Product
import com.example.mvvmarch.domain.repository.CartRepository
import com.example.mvvmarch.domain.usecase.GetProductsFromCartUseCase
import com.example.mvvmarch.domain.usecase.RemoveItemFromCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getProductsFromCartUseCase: GetProductsFromCartUseCase,
    private val removeItemFromCartUseCase: RemoveItemFromCartUseCase,
) : ViewModel() {

    private val _cartItems = MutableStateFlow<List<Product>>(emptyList())
    val cartItems: StateFlow<List<Product>> = _cartItems

    private val _reloadItems = MutableStateFlow(false)
    val reloadItems: StateFlow<Boolean> = _reloadItems

    fun getCartItems() {
        viewModelScope.launch(Dispatchers.IO) {
            _cartItems.value = getProductsFromCartUseCase()
        }
    }

    fun removeFromCart(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            removeItemFromCartUseCase(id)
            _reloadItems.value = !_reloadItems.value // Toggle the reload state
        }
    }


    /*fun removeFromCart(item: Product) {
        viewModelScope.launch {
            cartRepository.removeFromCart(item)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            cartRepository.clearCart()
        }
    }*/
}