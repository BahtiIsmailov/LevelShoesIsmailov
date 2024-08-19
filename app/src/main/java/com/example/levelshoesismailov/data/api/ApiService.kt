package com.example.levelshoesismailov.data.api

import retrofit2.http.GET

interface ApiService {
    @GET("v3/affb9b14-314d-461f-b47f-038ae4598e41")
    suspend fun getProducts(): ProductResponse
}