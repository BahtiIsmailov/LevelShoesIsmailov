package com.example.levelshoesismailov.navigation

import android.annotation.SuppressLint

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.levelshoesismailov.data.api.Image
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.levelshoesismailov.data.api.Product
import com.example.levelshoesismailov.presentation.ProductViewModel
import com.example.levelshoesismailov.presentation.ui.ProductDetailScreen
import com.example.levelshoesismailov.presentation.ui.ProductScreen
import com.example.levelshoesismailov.presentation.ui.WishListScreen

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NavigationComponent(
    navController: NavHostController,
    productViewModel: ProductViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = "productList"
    ) {

        composable("productList") {
            val favoriteStatus by productViewModel.favoriteStatus.collectAsState()
            ProductScreen(
                productResponse = productViewModel.productState.value ?: return@composable,
                onProductClick = { productId ->
                    navController.navigate("productDetail/$productId")
                },
                onWishListClick = {
                    navController.navigate("wishlist")
                },
                onWishlistToggle = { product ->
                    productViewModel.toggleFavorite(product)
                },
                isFavorite = { productId ->
                    productViewModel.checkIfFavorite(productId)
                    favoriteStatus[productId] ?: false
                }
            )
        }

        composable("wishlist") {
            productViewModel.getAllFavorites()
            WishListScreen(
                navController = navController,
                favoriteProducts = productViewModel.favorites,
                onProductClick = { productId ->
                    navController.navigate("productDetail/$productId")
                },
                onRemoveFromFavorites = { favoriteProduct ->
                    productViewModel.removeFromFavorites(
                        Product(
                            id = favoriteProduct.id,
                            sku = "",
                            name = favoriteProduct.name,
                            brand = favoriteProduct.brand,
                            image = Image(
                                url = favoriteProduct.imageUrl,
                                width = 607,
                                height = 850,
                                ),
                            price = favoriteProduct.price,
                            originalPrice = null,
                            badges = emptyList()
                        )
                    )
                }
            )
        }

        composable("productDetail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            val favoriteStatus by productViewModel.favoriteStatus.collectAsState()
            val product = productViewModel.productState.value?.items?.find { it.id == productId }
            product?.let { product ->
                ProductDetailScreen(
                    product = product,
                    isInWishList = {
                        productViewModel.checkIfFavorite(product.id)
                        favoriteStatus[product.id] ?: false
                    },
                    onAddToWishList = { productViewModel.toggleFavorite(product) },
                    onRemoveFromWishList = { productViewModel.removeFromWishList(product) },
                    onAddToCart = { },
                    onBackPress = { navController.popBackStack() }
                )
            }
        }
    }
}