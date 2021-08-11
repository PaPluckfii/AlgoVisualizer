package com.sumeet.algovisualizer.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LogsEntity::class],version = 1)
abstract class LogsDatabase : RoomDatabase() {
    abstract fun logsDao() : LogsDao

    companion object{

        private var INSTANCE : LogsDatabase? = null

        fun getDatabase(context: Context) : LogsDatabase {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context,LogsDatabase::class.java,"logs_db").build()
            }
            return INSTANCE!!
        }
    }
}