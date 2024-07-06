package com.example.a7minworkoutapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.concurrent.Flow

@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(historyentity: historyentity)

    @Query("SELECT * FROM 'Histroy-table'")
    fun fetchalldates():kotlinx.coroutines.flow.Flow<List<historyentity>>
}