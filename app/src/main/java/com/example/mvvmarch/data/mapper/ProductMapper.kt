package com.example.mvvmarch.data.mapper

import com.example.mvvmarch.data.model.ProductsItem
import com.example.mvvmarch.domain.capitalizeWords
import com.example.mvvmarch.domain.model.Product

fun ProductsItem.toDomain(): Product {
    return Product(
        category = this.category,
        description = this.description,
        id = this.id,
        image = this.image,
        price = this.price,
        rating = com.example.mvvmarch.domain.model.Rating(
            count = this.rating.count,
            rate = this.rating.rate
        ),
        title = this.title,
        orderQuantity = 0
    )
}