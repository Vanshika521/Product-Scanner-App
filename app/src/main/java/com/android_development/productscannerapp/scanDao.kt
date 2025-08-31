package com.android_development.productscanner

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScanDao {
    @Insert
    suspend fun insert(history: History): Long   // ✅ return Long (rowId)

    @Query("SELECT * FROM history ORDER BY timestamp DESC")
    fun getAll(): Flow<List<History>>
}
