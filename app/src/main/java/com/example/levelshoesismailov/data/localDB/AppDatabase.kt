package com.example.levelshoesismailov.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteProduct::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteProductDao(): FavoriteProductDao
}