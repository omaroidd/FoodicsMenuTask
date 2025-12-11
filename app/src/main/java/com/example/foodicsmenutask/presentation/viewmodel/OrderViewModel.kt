package com.example.foodicsmenutask.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodicsmenutask.data.repository.AppRepository
import com.example.foodicsmenutask.domain.model.OrderItem
import com.example.foodicsmenutask.domain.usecase.AddToOrderUseCase
import com.example.foodicsmenutask.domain.usecase.ClearOrderUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
class OrderViewModel(
    private val addToOrderUseCase: AddToOrderUseCase,
    private val clearOrderUseCase: ClearOrderUseCase,
    private val repository: AppRepository
) : ViewModel() {

    val orderItems: StateFlow<List<OrderItem>> =
        repository.getOrderItems()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    val total: StateFlow<Double> =
        orderItems
            .map { items ->
                items.sumOf { it.price * it.quantity }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                0.0
            )

    fun addProduct(productId: Int) {
        viewModelScope.launch {
            addToOrderUseCase(productId)
        }
    }

    fun clearOrder() {
        viewModelScope.launch {
            clearOrderUseCase()
        }
    }
}
