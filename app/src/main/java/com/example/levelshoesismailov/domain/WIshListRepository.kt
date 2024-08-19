package com.example.levelshoesismailov.domain

import com.example.levelshoesismailov.data.api.Product
import com.example.levelshoesismailov.data.localDB.FavoriteProduct
import com.example.levelshoesismailov.data.localDB.FavoriteProductDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WIshListRepository @Inject constructor(
    private val favoriteProductDao: FavoriteProductDao
) {

    suspend fun deleteProductFromRoom(product: Product) {
        withContext(Dispatchers.IO) {
            favoriteProductDao.delete(
                FavoriteProduct(
                    id = product.id,
                    name = product.name,
                    brand = product.brand,
                    imageUrl = product.image?.url ?: "",
                    price = product.price
                )
            )
        }
    }

    suspend fun insertProductFromRoom(product: Product){
        withContext(Dispatchers.IO) {
            favoriteProductDao.insert(
                FavoriteProduct(
                    id = product.id,
                    name = product.name,
                    brand = product.brand,
                    imageUrl = product.image?.url ?: "",
                    price = product.price
                )
            )
        }
    }

    suspend fun getFavoriteByIdFromRoom(productId: String) : Boolean{
        return withContext(Dispatchers.IO) {
            favoriteProductDao.getFavoriteById(productId) != null
        }
    }

    suspend fun getAllFavourites(): List<FavoriteProduct> {
        return withContext(Dispatchers.IO) {
            favoriteProductDao.getAllFavorites()
        }
    }
}