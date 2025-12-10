package com.example.foodicsmenutask.domain.model

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val price: Double,
    val category: Category
)