package com.example.mvvmarch.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmarch.domain.model.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product): Long

    @Query("SELECT * FROM products WHERE id = :productId")
    suspend fun getProductById(productId: Int): Product?

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()

    @Query("DELETE FROM products WHERE id = :productId")
    suspend fun deleteProductById(productId: Int)

    @Query("SELECT COUNT(*) FROM products")
    suspend fun getCartItemCount(): Int
}