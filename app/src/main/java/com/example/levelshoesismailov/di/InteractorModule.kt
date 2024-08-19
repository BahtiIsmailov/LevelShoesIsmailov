package com.example.levelshoesismailov.di

import com.example.levelshoesismailov.data.api.ApiService
import com.example.levelshoesismailov.data.localDB.FavoriteProductDao
import com.example.levelshoesismailov.domain.ProductInteractor
import com.example.levelshoesismailov.domain.WIshListInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorModule {

    @Provides
    @Singleton
    fun provideWishListInteractor(favoriteProductDao: FavoriteProductDao): WIshListInteractor {
        return WIshListInteractor(favoriteProductDao)
    }

    @Provides
    @Singleton
    fun provideProductInteractor(apiService: ApiService): ProductInteractor {
        return ProductInteractor(apiService)
    }
}