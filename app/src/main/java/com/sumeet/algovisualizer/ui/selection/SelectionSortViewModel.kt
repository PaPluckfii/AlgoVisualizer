package com.sumeet.algovisualizer.ui.selection


import androidx.lifecycle.ViewModel
import com.sumeet.algovisualizer.model.ArrayModelToSort
import com.sumeet.algovisualizer.model.room.LogsDatabase
import com.sumeet.algovisualizer.repository.SortingRepository


class SelectionSortViewModel(private val repository: SortingRepository) : ViewModel() {


    fun getNewArray(): ArrayModelToSort? {
        return repository.getNewArray()
    }

    suspend fun insertLog(log : String) {
        repository.insertLog(log)
    }

}