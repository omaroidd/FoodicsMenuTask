package com.example.foodicsmenutask.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodicsmenutask.data.local.database.dao.CategoryDao
import com.example.foodicsmenutask.data.local.database.dao.ProductDao
import com.example.foodicsmenutask.data.local.database.dao.OrderDao
import com.example.foodicsmenutask.data.local.database.entity.CategoryEntity
import com.example.foodicsmenutask.data.local.database.entity.ProductEntity
import com.example.foodicsmenutask.data.local.database.entity.OrderItemEntity

@Database(
    entities = [CategoryEntity::class, ProductEntity::class, OrderItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun orderDao(): OrderDao
}