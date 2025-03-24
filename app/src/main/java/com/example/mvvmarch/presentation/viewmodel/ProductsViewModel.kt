package com.example.mvvmarch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmarch.data.model.ProductsItem
import com.example.mvvmarch.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {
    private val _productsList = MutableStateFlow(emptyList<ProductsItem>())
    val productsList: StateFlow<List<ProductsItem>> = _productsList

    fun getProductsByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _productsList.value = getProductsUseCase(category)
        }
    }
}