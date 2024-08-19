package com.example.levelshoesismailov.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.FlowRow
import coil.compose.rememberImagePainter
import com.example.levelshoesismailov.data.api.Product
import com.example.levelshoesismailov.data.api.ProductResponse

@Composable
fun ProductScreen(
    productResponse: ProductResponse,
    onProductClick: (String) -> Unit,
    onWishListClick: () -> Unit,
    onWishlistToggle: (Product) -> Unit,
    isFavorite: (String) -> Boolean
) {
    Column {
        productResponse.banner?.let { banner ->
            BannerView(banner)
        }
        IconButton(onClick = onWishListClick) {
            Icon(Icons.Default.Favorite, contentDescription = "Wishlist")
        }
        ProductGrid(
            products = productResponse.items,
            onProductClick = onProductClick,
            onWishlistToggle = onWishlistToggle,
            isFavorite = isFavorite
        )
    }
}


@Composable
fun ProductGrid(isFavorite: (String) -> Boolean, products: List<Product>, onProductClick: (String) -> Unit, onWishlistToggle: (Product) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Два столбца в сетке
        modifier = Modifier.padding(horizontal = 8.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products) { product ->
            ProductItem(
                product = product,
                onProductClick = { onProductClick(it) },
                isFavorite = isFavorite(product.id),
                onWishlistToggle = {
                    onWishlistToggle(product)
                })
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductItem(
    product: Product,
    isFavorite: Boolean,
    onProductClick: (String) -> Unit,
    onWishlistToggle: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onProductClick(product.id) }
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = rememberImagePainter(data = product.image?.url),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            if (product.badges.isNotEmpty()) {
                FlowRow(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(4.dp),
                    maxItemsInEachRow = 2
                ) {
                    product.badges.forEach { badge ->
                        Text(
                            text = badge,
                            style = MaterialTheme.typography.labelSmall.copy(color = Color.White),
                            modifier = Modifier
                                .padding(4.dp)
                                .background(MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(4.dp))
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        )
                    }
                }
            }
            IconButton(
                onClick = onWishlistToggle,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Wishlist"
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = product.brand,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = product.name,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            softWrap = true
        )
        Text(
            text = "${product.price} AED",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        if (product.originalPrice != null) {
            Text(
                text = "${product.originalPrice} AED",
                style = MaterialTheme.typography.bodySmall.copy(textDecoration = TextDecoration.LineThrough),
                color = Color.Gray
            )
        }
    }
}


