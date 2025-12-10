package com.example.foodicsmenutask.di

import androidx.room.Room
import com.example.foodicsmenutask.data.local.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "foodics_menu_app_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDatabase>().categoryDao() }
    single { get<AppDatabase>().productDao() }
    single { get<AppDatabase>().orderDao() }
}
