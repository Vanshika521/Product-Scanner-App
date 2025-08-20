package com.android_development.productscannerapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//    }
//}

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        // QR Code scan launcher
        val barcodeLauncher = registerForActivityResult(ScanContract()) { result ->
            result.contents?.let { qrCode ->
                viewModel.getProduct(qrCode)  // fetch product from API
            }
        }

        findViewById<Button>(R.id.scanBtn).setOnClickListener {
            barcodeLauncher.launch(ScanOptions().setPrompt("Scan QR Code"))
        }

        // Observe product and open ProductActivity
        viewModel.product.observe(this) { product ->
            product?.let {
                val intent = Intent(this, ProductActivity::class.java)
                intent.putExtra("product", it)
                startActivity(intent)
            }
        }
    }
}
