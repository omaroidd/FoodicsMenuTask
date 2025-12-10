package com.example.foodicsmenutask.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class CategoryDto(
    val id: Int,
    val name: String
)