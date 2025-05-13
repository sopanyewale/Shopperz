package com.example.mvvmarch.data.model


data class ProductsItem(
    val category: String = "",
    val description: String = "",
    val id: Int = 0,
    val image: String = "",
    val price: Double = 0.0,
    val rating: Rating = Rating(),
    val title: String = "Blank Title"
)