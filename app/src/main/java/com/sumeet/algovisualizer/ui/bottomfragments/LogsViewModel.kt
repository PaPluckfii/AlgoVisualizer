package com.sumeet.algovisualizer.ui.bottomfragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sumeet.algovisualizer.model.room.LogsDao
import com.sumeet.algovisualizer.model.room.LogsEntity
import com.sumeet.algovisualizer.repository.SortingRepository
import kotlinx.coroutines.flow.Flow

class LogsViewModel(
    private val repository : SortingRepository
) : ViewModel() {

    suspend fun getLiveLogs() : Flow<List<LogsEntity>> {
        return repository.getLiveLogs()
    }

}