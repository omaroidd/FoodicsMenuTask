package com.example.foodicsmenutask.domain.usecase

import com.example.foodicsmenutask.data.repository.AppRepository

class AddToOrderUseCase(private val repository: AppRepository) {
    suspend operator fun invoke(productId: Int) {
        repository.addToOrder(productId)
    }
}