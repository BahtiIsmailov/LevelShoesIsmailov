package com.example.levelshoesismailov.di

import com.example.levelshoesismailov.data.api.ApiService
import com.example.levelshoesismailov.data.localDB.FavoriteProductDao
import com.example.levelshoesismailov.domain.ProductRepository
import com.example.levelshoesismailov.domain.WIshListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWishListRepository(favoriteProductDao: FavoriteProductDao): WIshListRepository {
        return WIshListRepository(favoriteProductDao)
    }

    @Provides
    @Singleton
    fun provideProductRepository(apiService: ApiService): ProductRepository {
        return ProductRepository(apiService)
    }
}