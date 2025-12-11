package com.example.foodicsmenutask.domain.usecase

import com.example.foodicsmenutask.data.repository.AppRepository

class RefreshCategoriesUseCase(private val repository: AppRepository) {
    suspend operator fun invoke() {
        repository.refreshCategories()
    }
}