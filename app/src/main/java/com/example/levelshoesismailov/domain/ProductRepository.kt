package com.example.levelshoesismailov.domain

import com.example.levelshoesismailov.data.api.ApiService
import com.example.levelshoesismailov.data.api.ProductResponse
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun fetchProducts(): ProductResponse {
        return apiService.getProducts()
    }
}