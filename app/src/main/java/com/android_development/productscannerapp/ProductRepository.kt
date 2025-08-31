package com.android_development.productscanner

import android.app.Application
import androidx.room.Room
import com.android_development.productscannerapp.ApiClient
import com.android_development.productscanner.Scan

class ProductRepository(app: Application) {
    private val db = Room.databaseBuilder(app, AppDatabase::class.java, "app_db").build()
    private val dao = db.scanDao()

    fun getHistory() = dao.getAll()
    suspend fun fetchAndSaveProduct(code: String, lat: Double, lng: Double): Scan {
        // temporary hardcoded phone, or fetch from user prefs
        val phone = "1234567890"

        val result = ApiClient.api.verifyQRCode(phone, code)
        val scan = Scan(
            id = code,
            name = result.name,
            description = result.description,
            imageUrl = result.image
        )
        dao.insert(
            History(
                qrCode = code,
                productName = result.name,
                timestamp = System.currentTimeMillis()
            )
        )
        return scan
    }
}
