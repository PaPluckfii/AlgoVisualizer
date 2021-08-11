package com.sumeet.algovisualizer.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "logs")
data class LogsEntity(
    @ColumnInfo (name = "log") var log : String
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}