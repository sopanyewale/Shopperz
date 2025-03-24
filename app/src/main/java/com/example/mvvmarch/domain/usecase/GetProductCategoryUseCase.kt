package com.example.mvvmarch.domain.usecase

import com.example.mvvmarch.R
import com.example.mvvmarch.domain.model.Category
import com.example.mvvmarch.domain.repository.ProductRepository
import java.util.Locale
import javax.inject.Inject

class GetProductCategoryUseCase @Inject constructor(private val repository: ProductRepository) {
    suspend operator fun invoke(): List<Category> {
        val categories = repository.getCategories()

        return categories.map {
            when (it) {
                "electronics" -> Category(it.capitalizeWords(), R.drawable.electronics)
                "jewelery" -> Category(it.capitalizeWords(), R.drawable.jewellary)
                "men's clothing" -> Category(it.capitalizeWords(), R.drawable.mens_clothing)
                "women's clothing" -> Category(it.capitalizeWords(), R.drawable.womens_clothing)
                else -> Category(it, R.drawable.ic_launcher_foreground)
            }
        }
    }

    private fun String.capitalizeWords(): String {
        return this.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ENGLISH
            ) else it.toString()
        }
    }
}