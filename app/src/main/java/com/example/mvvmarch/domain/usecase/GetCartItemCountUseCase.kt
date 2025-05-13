package com.example.mvvmarch.domain.usecase

import com.example.mvvmarch.domain.repository.CartRepository
import javax.inject.Inject

class GetCartItemCountUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(): Int {
        return cartRepository.getCartItemCount()
    }
}