package com.example.mvvmarch.domain.usecase

import com.example.mvvmarch.R
import com.example.mvvmarch.domain.capitalizeWords
import com.example.mvvmarch.domain.model.Category
import com.example.mvvmarch.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductCategoryUseCase @Inject constructor(private val repository: ProductRepository) {
    suspend operator fun invoke(): List<Category> {
        val categories = repository.getCategories()

        return categories.map {
            when (it) {
                "electronics" -> Category(it, R.drawable.electronics, it.capitalizeWords())
                "jewelery" -> Category(it, R.drawable.jewellary, it.capitalizeWords())
                "men's clothing" -> Category(it, R.drawable.mens_clothing, it.capitalizeWords())
                "women's clothing" -> Category(it, R.drawable.womens_clothing, it.capitalizeWords())
                else -> Category(it, R.drawable.ic_launcher_foreground, it.capitalizeWords())
            }
        }
    }
}