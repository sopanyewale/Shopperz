package com.example.mvvmarch.domain.usecase

import com.example.mvvmarch.domain.repository.CartRepository
import javax.inject.Inject

class RemoveItemFromCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(id: Int) {
        cartRepository.removeFromCart(id)
    }
}