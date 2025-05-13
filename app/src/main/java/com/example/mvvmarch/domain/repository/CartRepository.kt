package com.example.mvvmarch.domain.repository

import com.example.mvvmarch.domain.model.Product

interface CartRepository {

    suspend fun getCartItems(): List<Product>

    suspend fun addToCart(item: Product): Long

    suspend fun removeFromCart(item: Product)

    suspend fun clearCart()

    suspend fun getTotalPrice(): Double

    suspend fun getCartItemCount(): Int
}