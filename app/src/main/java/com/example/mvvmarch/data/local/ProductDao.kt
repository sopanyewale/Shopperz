package com.example.mvvmarch.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmarch.domain.model.Product
import kotlinx.coroutines.flow.Flow

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

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<Product>

    //Observable type of query to get notified for the changes in products table
    @Query("SELECT COUNT(*) FROM products")
    fun getCartCount(): Flow<Int>
}