package com.sumeet.algovisualizer.model.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LogsDao {

    @Insert
    suspend fun insertIntoLogs(log : String)

    @Query("SELECT * FROM logs")
    suspend fun getLogs() : Flow<List<LogsEntity>>

    @Query("DELETE FROM logs")
    suspend fun deleteAllLogs()

}