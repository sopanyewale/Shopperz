package com.example.mvvmarch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmarch.domain.model.Category
import com.example.mvvmarch.domain.usecase.GetProductCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getProductCategoryUseCase: GetProductCategoryUseCase
) : ViewModel() {
    private val _productCategories = MutableStateFlow(emptyList<Category>())
    val productCategories: StateFlow<List<Category>> = _productCategories

    fun getProductCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            _productCategories.value = getProductCategoryUseCase()
        }

    }

    fun onCategorySelected(category: Category) {
        println("Category selected: ${category.name}")
    }
}