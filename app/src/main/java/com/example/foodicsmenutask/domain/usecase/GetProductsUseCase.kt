package com.example.foodicsmenutask.domain.usecase

import com.example.foodicsmenutask.data.repository.AppRepository
import com.example.foodicsmenutask.domain.model.Product
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(private val repository: AppRepository) {
    operator fun invoke(): Flow<List<Product>> = repository.getProducts()
}