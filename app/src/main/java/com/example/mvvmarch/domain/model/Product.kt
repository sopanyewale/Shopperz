package com.example.mvvmarch.domain.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    var category: String = "",
    var description: String = "",
    @PrimaryKey var id: Int = 0,
    var image: String = "",
    var price: Double = 0.0,
    @Ignore var rating: Rating = Rating(),
    var title: String = "Blank Title",
    var orderQuantity: Int = 0
)