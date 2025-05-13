package com.example.mvvmarch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmarch.di.common.Dispatcher
import com.example.mvvmarch.domain.model.Product
import com.example.mvvmarch.domain.usecase.AddProductToCartDatabaseUseCase
import com.example.mvvmarch.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val dispatcher: Dispatcher,
    private val getProductsUseCase: GetProductsUseCase,
    private val addProductToCartDatabaseUseCase: AddProductToCartDatabaseUseCase
) : ViewModel() {
    private val _productsList = MutableStateFlow(emptyList<Product>())
    val productsList: StateFlow<List<Product>> = _productsList

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent: SharedFlow<String> = _toastEvent

    private val _selectedProduct = MutableStateFlow(Product())
    val selectedProduct: StateFlow<Product> = _selectedProduct

    fun getProductsByCategory(category: String) {
        viewModelScope.launch(dispatcher.io) {
            _productsList.value = getProductsUseCase(category)
        }
    }

    fun onProductSelected(product: Product) {
        _selectedProduct.value = product
    }

    fun addProductToCart(product: Product) {
        viewModelScope.launch(dispatcher.io) {
            val result = addProductToCartDatabaseUseCase(product)
            if (result > 0) {
                _toastEvent.emit("Product added to cart")
            } else {
                _toastEvent.emit("Failed to add product to cart")
            }
        }
    }
}