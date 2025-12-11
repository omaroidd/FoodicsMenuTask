package com.example.foodicsmenutask.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_items")
data class OrderItemEntity(
    @PrimaryKey val productId: Int,
    val productName: String,
    val price: Double,
    val quantity: Int,
    val image: String
)