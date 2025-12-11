package com.example.foodicsmenutask.di

import com.example.foodicsmenutask.domain.usecase.AddToOrderUseCase
import com.example.foodicsmenutask.domain.usecase.ClearOrderUseCase
import com.example.foodicsmenutask.domain.usecase.GetCategoriesUseCase
import com.example.foodicsmenutask.domain.usecase.GetProductsUseCase
import com.example.foodicsmenutask.domain.usecase.RefreshCategoriesUseCase
import com.example.foodicsmenutask.domain.usecase.RefreshProductsUseCase
import com.example.foodicsmenutask.domain.usecase.SearchProductsUseCase
import com.example.foodicsmenutask.presentation.viewmodel.OrderViewModel
import com.example.foodicsmenutask.presentation.viewmodel.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    single { GetCategoriesUseCase(get()) }
    single { GetProductsUseCase(get()) }
    single { RefreshProductsUseCase(get()) }
    single { RefreshCategoriesUseCase(get()) }
    single { SearchProductsUseCase(get()) }
    single { AddToOrderUseCase(get()) }
    single { ClearOrderUseCase(get()) }
}

val viewModelModule = module {
    viewModel { ProductsViewModel(get(), get(), get(), get(), get()) }
    viewModel { OrderViewModel(get(), get(), get()) }
}