package com.example.mvvmarch.domain.repository

import com.example.mvvmarch.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    suspend fun getCartItems(): List<Product>

    suspend fun addToCart(item: Product): Long

    suspend fun removeFromCart(productId: Int)

    suspend fun clearCart()

    suspend fun getTotalPrice(): Double

    suspend fun getCartItemCount(): Int

    fun getCartCount(): Flow<Int>
}