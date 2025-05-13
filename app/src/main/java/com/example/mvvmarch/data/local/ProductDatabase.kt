package com.example.mvvmarch.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmarch.domain.model.Product

@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false
)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}