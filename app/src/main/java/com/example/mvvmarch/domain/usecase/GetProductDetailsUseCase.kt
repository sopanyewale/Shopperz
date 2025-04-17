package com.example.mvvmarch.domain.usecase

import com.example.mvvmarch.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(private val repository: ProductRepository) {
    suspend operator fun invoke(productId: Int) = repository.getProductDetails(productId)
}