package com.example.mvvmarch.data.repository

import com.example.mvvmarch.data.local.ProductDao
import com.example.mvvmarch.domain.model.Product
import com.example.mvvmarch.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(private val dao: ProductDao): CartRepository {
    override suspend fun getCartItems(): List<Product> {
        return dao.getAllProducts()
    }

    override suspend fun addToCart(item: Product): Long {
        return dao.insertProduct(item)
    }

    override suspend fun removeFromCart(productId: Int) {
        dao.deleteProductById(productId)
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

    override fun getCartCount(): Flow<Int> {
        return dao.getCartCount()
    }

}