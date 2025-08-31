package com.android_development.productscanner

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import coil.load

class ProductActivity : ComponentActivity() {
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val img = findViewById<ImageView>(R.id.productImage)
        val name = findViewById<TextView>(R.id.productName)
        val desc = findViewById<TextView>(R.id.productDesc)

        viewModel.product.observe(this) {
            name.text = it.name
            desc.text = it.description
            img.load(it.imageUrl)
        }
    }
}
