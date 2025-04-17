package com.example.mvvmarch.data.repository

import com.example.mvvmarch.data.model.ProductsItem
import com.example.mvvmarch.data.remote.ApiService
import com.example.mvvmarch.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    ProductRepository {

    override suspend fun getCategories() = apiService.getCategories()

    override suspend fun getProducts(category: String) = apiService.getProducts(category)

    override suspend fun getProductDetails(productId: Int) = apiService.getProductDetails(productId)
}