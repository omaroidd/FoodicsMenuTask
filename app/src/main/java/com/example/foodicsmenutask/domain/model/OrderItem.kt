package com.example.foodicsmenutask.domain.model

data class OrderItem(
    val productId: Int,
    val productName: String,
    val price: Double,
    val quantity: Int,
    val image: String
)