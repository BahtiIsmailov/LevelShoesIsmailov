package com.example.levelshoesismailov.data.localDB

import androidx.room.*

@Dao
interface FavoriteProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteProduct: FavoriteProduct)

    @Delete
    suspend fun delete(favoriteProduct: FavoriteProduct)

    @Query("SELECT * FROM favorite_products")
    suspend fun getAllFavorites(): List<FavoriteProduct>

    @Query("SELECT * FROM favorite_products WHERE id = :productId LIMIT 1")
    suspend fun getFavoriteById(productId: String): FavoriteProduct?
}