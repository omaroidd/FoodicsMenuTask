package com.example.foodicsmenutask

import android.app.Application
import com.example.foodicsmenutask.di.databaseModule
import com.example.foodicsmenutask.di.networkModule
import com.example.foodicsmenutask.di.repositoryModule
import com.example.foodicsmenutask.di.useCaseModule
import com.example.foodicsmenutask.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                databaseModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}