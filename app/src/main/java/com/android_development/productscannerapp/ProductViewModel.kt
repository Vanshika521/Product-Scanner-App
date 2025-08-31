package com.android_development.productscanner

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class ProductViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = ProductRepository(app)

    val history = repo.getHistory().asLiveData()
    val product = MutableLiveData<Scan>()

    fun fetchProduct(code: String, lat: Double, lng: Double) {
        viewModelScope.launch {
            try {
                val result = repo.fetchAndSaveProduct(code, lat, lng)
                product.postValue(result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
