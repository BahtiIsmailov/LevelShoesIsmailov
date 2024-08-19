package com.example.levelshoesismailov.data.api

import com.google.gson.annotations.SerializedName

data class Banner(
    @SerializedName("title") val title: String,
    @SerializedName("message") val message: String
)

data class Image(
    @SerializedName("url") val url: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int
)

data class Product(
    @SerializedName("id") val id: String,
    @SerializedName("sku") val sku: String,
    @SerializedName("image") val image: Image?,
    @SerializedName("brand") val brand: String,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Double,
    @SerializedName("originalPrice") val originalPrice: Double?,
    @SerializedName("badges") val badges: List<String>
)

data class ProductResponse(
    @SerializedName("title") val title: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("banner") val banner: Banner?,
    @SerializedName("items") val items: List<Product>
)
