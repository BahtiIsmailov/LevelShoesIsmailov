package com.example.levelshoesismailov.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelshoesismailov.data.api.Product
import com.example.levelshoesismailov.data.api.ProductResponse
import com.example.levelshoesismailov.data.localDB.FavoriteProduct
import com.example.levelshoesismailov.domain.ProductRepository
import com.example.levelshoesismailov.domain.WIshListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val wIshListRepository: WIshListRepository,
    private val productRepository: ProductRepository,
) : ViewModel() {

    private val _favoriteStatus = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val favoriteStatus: StateFlow<Map<String, Boolean>> = _favoriteStatus

    private val _productState = mutableStateOf<ProductResponse?>(null)
    val productState: State<ProductResponse?> = _productState

    private val _favorites = mutableStateListOf<FavoriteProduct>()
    val favorites: List<FavoriteProduct> get() = _favorites


    init { fetchProducts() }

    private fun fetchProducts() {
        viewModelScope.launch {
            runCatching {
                productRepository.fetchProducts()
            }.fold(
                onSuccess = { response ->
                    _productState.value = response
                },
                onFailure = {
                    //Here is Error data
                }
            )
        }
    }

    fun removeFromWishList(product: Product) { _favorites.remove(mapToFavoriteProduct(product)) }

    fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            val currentStatus = _favoriteStatus.value[product.id] ?: false
            if (currentStatus) {
                wIshListRepository.deleteProductFromRoom(product)
            } else {
                wIshListRepository.insertProductFromRoom(product)
            }
            _favoriteStatus.value = _favoriteStatus.value
                .toMutableMap()
                .apply { put(product.id, !currentStatus) }
        }
    }

    fun removeFromFavorites(product: Product) {
        viewModelScope.launch {
            val favouriteProduct = FavoriteProduct(
                id = product.id,
                name = product.name,
                brand = product.brand,
                imageUrl = product.image?.url ?: "",
                price = product.price
            )
            _favorites.remove(favouriteProduct)
            wIshListRepository.deleteProductFromRoom(product)
        }
    }

    fun checkIfFavorite(productId: String) {
        viewModelScope.launch {
            val isFavorite = wIshListRepository.getFavoriteByIdFromRoom(productId)
            _favoriteStatus.value = _favoriteStatus.value
                .toMutableMap()
                .apply { put(productId, isFavorite) }
        }
    }

    fun getAllFavorites() {
        viewModelScope.launch {
            val allFavorites = wIshListRepository.getAllFavourites()
            allFavorites.forEach { product ->
                val exists = favorites.any { it.id == product.id }
                if (!exists) {
                    _favorites.add(product)
                }
            }
        }
    }

    private fun mapToFavoriteProduct(product: Product) = FavoriteProduct(
            id = product.id,
            name = product.name,
            brand = product.brand,
            imageUrl = product.image?.url ?: "",
            price = product.price
        )
}
