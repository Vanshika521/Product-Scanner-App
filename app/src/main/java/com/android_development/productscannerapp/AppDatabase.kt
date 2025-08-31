package com.android_development.productscanner

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [History::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scanDao(): ScanDao   // ✅ matches exactly
}
