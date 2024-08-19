package com.example.levelshoesismailov.di

import android.content.Context
import androidx.room.Room
import com.example.levelshoesismailov.data.localDB.AppDatabase
import com.example.levelshoesismailov.data.localDB.FavoriteProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val DATA_BASE_NAME = "favorite_products_db"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATA_BASE_NAME
        ).build()
    }

    @Provides
    fun provideFavoriteProductDao(appDatabase: AppDatabase): FavoriteProductDao {
        return appDatabase.favoriteProductDao()
    }
}
