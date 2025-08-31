package com.android_development.productscanner

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class History(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val qrCode: String,
    val productName: String,
    val timestamp: Long
)
