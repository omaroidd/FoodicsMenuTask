package com.example.foodicsmenutask.data.remote

import com.example.foodicsmenutask.config.AppConfig
import com.example.foodicsmenutask.data.remote.dto.CategoryDto
import com.example.foodicsmenutask.data.remote.dto.ProductDto
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class RemoteDataSource(private val client: HttpClient) {
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    suspend fun fetchCategories(): List<CategoryDto> {
        return client.get(AppConfig.BASE_URL + AppConfig.CATEGORIES_PATH).body()
    }


    suspend fun fetchProducts(): List<ProductDto> {
        return client.get(AppConfig.BASE_URL + AppConfig.PRODUCTS_PATH).body()
    }
}