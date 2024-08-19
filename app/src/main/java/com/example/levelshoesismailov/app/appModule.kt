package com.example.levelshoesismailov.app

import androidx.room.Room
import com.example.levelshoesismailov.data.api.ApiService
import com.example.levelshoesismailov.data.localDB.AppDatabase
import com.example.levelshoesismailov.domain.ProductRepository
import com.example.levelshoesismailov.domain.WIshListRepository
import com.example.levelshoesismailov.presentation.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val DATA_BASE_NAME = "favorite_products_db"
const val BASE_URL = "https://run.mocky.io/"

val appModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java, DATA_BASE_NAME
        ).build()
    }

    single { get<AppDatabase>().favoriteProductDao() }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(ApiService::class.java) }

    single { WIshListRepository(get()) }
    single { ProductRepository(get()) }
    viewModel { ProductViewModel(get(), get()) }
}