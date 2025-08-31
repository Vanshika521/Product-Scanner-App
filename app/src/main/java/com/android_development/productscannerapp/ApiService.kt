package com.android_development.productscannerapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class ProductResponse(
    val name: String,
    val description: String,
    val image: String
)

interface ApiService {
    @GET("api/v1/consumers/verify_qr_code")
    suspend fun verifyQRCode(
        @Query("phone_number") phone: String,
        @Query("qr_code") qrCode: String
    ): ProductResponse
}

object ApiClient {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://newdemo.onspotsolutions.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
