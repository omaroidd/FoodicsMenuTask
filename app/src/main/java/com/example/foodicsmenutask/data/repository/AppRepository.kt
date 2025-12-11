package com.example.foodicsmenutask.data.repository

import com.example.foodicsmenutask.domain.model.Category
import com.example.foodicsmenutask.domain.model.OrderItem
import com.example.foodicsmenutask.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getCategories(): Flow<List<Category>>
    fun getProducts(): Flow<List<Product>>
    fun searchProducts(query: String): Flow<List<Product>>
    suspend fun refreshCategories()
    suspend fun refreshProducts()
    suspend fun addToOrder(productId: Int)
    suspend fun clearOrder()
    fun getOrderItems(): Flow<List<OrderItem>>
}
