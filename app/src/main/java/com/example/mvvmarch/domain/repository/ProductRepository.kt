package com.example.mvvmarch.domain.repository

import com.example.mvvmarch.data.model.ProductsItem

interface ProductRepository {

    suspend fun getCategories(): List<String>

    suspend fun getProducts(category: String): List<ProductsItem>
}