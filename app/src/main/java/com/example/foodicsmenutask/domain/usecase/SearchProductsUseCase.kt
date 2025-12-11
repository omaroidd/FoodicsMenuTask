package com.example.foodicsmenutask.domain.usecase

import com.example.foodicsmenutask.data.repository.AppRepository
import com.example.foodicsmenutask.domain.model.Product
import kotlinx.coroutines.flow.Flow

class SearchProductsUseCase(private val repository: AppRepository) {
    operator fun invoke(query: String): Flow<List<Product>> = repository.searchProducts(query)
}