package com.example.mvvmarch.data.remote

import com.example.mvvmarch.data.model.ProductsItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("products/categories")
    suspend fun getCategories(): List<String>

    @GET("products/category/{category}")
    suspend fun getProducts(@Path("category") category: String): List<ProductsItem>
}