package com.example.foodicsmenutask.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val price: Double,
    val category: CategoryDto
)