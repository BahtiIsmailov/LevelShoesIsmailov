package com.example.levelshoesismailov.data.localDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_products")
data class FavoriteProduct(
    @PrimaryKey val id: String,
    val name: String,
    val brand: String,
    val imageUrl: String,
    val price: Double
)