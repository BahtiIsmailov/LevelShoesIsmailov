package com.example.levelshoesismailov.domain

import com.example.levelshoesismailov.data.api.ApiService
import com.example.levelshoesismailov.data.api.ProductResponse

class ProductRepository(
    private val apiService: ApiService
) {

    suspend fun fetchProducts(): ProductResponse {
        return apiService.getProducts()
    }
}