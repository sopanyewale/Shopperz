package com.example.mvvmarch.domain.repository

import com.example.mvvmarch.data.model.ProductsItem
import com.example.mvvmarch.domain.model.Category

interface ProductRepository {

    suspend fun getCategories(): List<String>

    suspend fun getProducts(category: String): List<ProductsItem>
}