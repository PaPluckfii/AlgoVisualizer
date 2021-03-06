package com.sumeet.algovisualizer.repository

import androidx.lifecycle.MutableLiveData
import com.github.mikephil.charting.data.BarEntry
import com.sumeet.algovisualizer.R
import com.sumeet.algovisualizer.model.ArrayModelToSort
import com.sumeet.algovisualizer.model.room.LogsEntity
import kotlinx.coroutines.flow.Flow
import kotlin.random.Random

class SortingRepository() {

    private lateinit var arrayModelToSort: MutableLiveData<ArrayModelToSort>

    fun getNewArray(): ArrayModelToSort? {
        var i = 1f
        arrayModelToSort = MutableLiveData(ArrayModelToSort(
            MutableList(10) {
                BarEntry(
                    i++,
                    Random.nextInt(1, 20).toFloat()
                )
            },
            MutableList(10){
               R.color.theme_orange_variant
            }
        ))
        return arrayModelToSort.value
    }

    suspend fun getLiveLogs(): Flow<List<LogsEntity>> {
        return logsDao.getLogs()
    }

    suspend fun insertLog(log: String) {
        logsDao.insertIntoLogs(log)
    }

}