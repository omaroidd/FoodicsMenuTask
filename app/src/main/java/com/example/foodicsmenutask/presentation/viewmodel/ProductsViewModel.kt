package com.example.foodicsmenutask.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodicsmenutask.domain.model.Product
import com.example.foodicsmenutask.domain.usecase.GetProductsUseCase
import com.example.foodicsmenutask.domain.usecase.RefreshCategoriesUseCase
import com.example.foodicsmenutask.domain.usecase.RefreshProductsUseCase
import com.example.foodicsmenutask.domain.usecase.SearchProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
class ProductsViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val searchProductsUseCase: SearchProductsUseCase,
    private val refreshProductsUseCase: RefreshProductsUseCase,
    private val refreshCategoriesUseCase: RefreshCategoriesUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    val products: StateFlow<List<Product>> =
        _query
            .debounce(300)
            .flatMapLatest { q ->
                if (q.isEmpty()) {
                    getProductsUseCase()
                } else {
                    searchProductsUseCase(q)
                }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    init {
        refreshAllData()
    }

    private fun refreshAllData() {
        viewModelScope.launch {
            try {
                _loading.value = true

                refreshCategoriesUseCase()
                refreshProductsUseCase()

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }

    fun setQuery(q: String) {
        _query.value = q
    }
}

