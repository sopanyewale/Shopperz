package com.example.mvvmarch.domain.repository

import com.example.mvvmarch.data.model.ProductsItem
import com.example.mvvmarch.domain.model.Product

interface ProductRepository {

    suspend fun getCategories(): List<String>

    suspend fun getProducts(category: String): List<Product>

    suspend fun getProductDetails(productId: Int): ProductsItem
}