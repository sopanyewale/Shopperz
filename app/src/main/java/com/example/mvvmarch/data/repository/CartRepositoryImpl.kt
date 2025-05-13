package com.example.mvvmarch.data.repository

import com.example.mvvmarch.data.local.ProductDao
import com.example.mvvmarch.domain.model.Product
import com.example.mvvmarch.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(private val dao: ProductDao): CartRepository {
    override suspend fun getCartItems(): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun addToCart(item: Product): Long {
        return dao.insertProduct(item)
    }

    override suspend fun removeFromCart(item: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun clearCart() {
        TODO("Not yet implemented")
    }

    override suspend fun getTotalPrice(): Double {
        TODO("Not yet implemented")
    }

    override suspend fun getCartItemCount(): Int {
        return dao.getCartItemCount()
    }

}