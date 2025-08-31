package com.android_development.productscanner

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.google.zxing.integration.android.IntentIntegrator
import com.google.android.gms.location.LocationServices

class MainActivity : ComponentActivity() {
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // start QR scan immediately
        val integrator = IntentIntegrator(this)
        integrator.setPrompt("Scan a QR code")
        integrator.setOrientationLocked(false)
        integrator.initiateScan()
    }

    @SuppressLint("MissingPermission")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null && result.contents != null) {
            val client = LocationServices.getFusedLocationProviderClient(this)
            client.lastLocation.addOnSuccessListener { loc ->
                if (loc != null) {
                    viewModel.fetchProduct(result.contents, loc.latitude, loc.longitude)
                    startActivity(Intent(this, ProductActivity::class.java))
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
