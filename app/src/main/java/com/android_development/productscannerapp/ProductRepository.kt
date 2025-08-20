package com.android_development.productscannerapp


    // Handles API calls and saving scan history
class ProductRepository(context: Context) {

        // Simple Room database
        private val db = Room.databaseBuilder(context, AppDatabase::class.java, "scan_db").build()

        // Retrofit for API
        private val api = Retrofit.Builder()
            .baseUrl("https://newdemo.onspotsolutions.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        // Fetch product from API
        fun getProduct(qrCode: String, onResult: (ProductResponse?) -> Unit) {
            api.verifyQRCode("9320653961", qrCode).enqueue(object: Callback<ProductResponse> {
                override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                    onResult(response.body())
                }
                override fun onFailure(call: Call<ProductResponse>, t: Throwable) { onResult(null) }
            })
        }

        // Save scan in local database
        fun saveScan(product: ProductResponse, qrCode: String) {
            Thread {
                db.scanDao().insert(
                    ScanEntity(qrCode = qrCode, productName = product.name ?: "", timestamp = System.currentTimeMillis())
                )
            }.start()
        }
    }

}