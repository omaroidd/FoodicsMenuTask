package com.example.foodicsmenutask.domain.usecase

import com.example.foodicsmenutask.data.repository.AppRepository
import com.example.foodicsmenutask.domain.model.Category
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase(private val repository: AppRepository) {
    operator fun invoke(): Flow<List<Category>> = repository.getCategories()
}
