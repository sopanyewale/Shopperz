package com.example.mvvmarch.domain.usecase

import com.example.mvvmarch.data.repository.ProductRepositoryImpl
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val repository: ProductRepositoryImpl) {
    suspend operator fun invoke(category: String) = repository.getProducts(category)
}