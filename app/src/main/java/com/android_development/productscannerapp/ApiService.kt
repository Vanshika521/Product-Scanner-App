package com.android_development.productscannerapp


import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    // This handles our API call
        @POST("api/v1/consumers/verify_qr_code")
        fun verifyQRCode(
            @Query("phone_number") phone: String,  // fixed phone number for now
            @Query("qr_code") qrCode: String       // QR code scanned
        ): Call<ProductResponse>
    }

