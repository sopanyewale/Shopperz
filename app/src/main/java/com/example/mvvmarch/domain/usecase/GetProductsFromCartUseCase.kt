package com.example.mvvmarch.domain.usecase

import com.example.mvvmarch.domain.model.Product
import com.example.mvvmarch.domain.repository.CartRepository
import javax.inject.Inject

class GetProductsFromCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(): List<Product> {
        return cartRepository.getCartItems()
    }
}