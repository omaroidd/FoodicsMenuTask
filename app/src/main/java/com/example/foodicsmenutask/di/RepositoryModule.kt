package com.example.foodicsmenutask.di

import com.example.foodicsmenutask.data.repository.AppRepository
import com.example.foodicsmenutask.data.repository.AppRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<AppRepository> { AppRepositoryImpl(get(), get(), get(), get()) }
}