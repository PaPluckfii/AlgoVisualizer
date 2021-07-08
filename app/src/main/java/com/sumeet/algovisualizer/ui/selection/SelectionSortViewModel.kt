package com.sumeet.algovisualizer.ui.selection


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sumeet.algovisualizer.model.ArrayModelToSort
import com.sumeet.algovisualizer.repository.SortingRepository


class SelectionSortViewModel : ViewModel() {

    private val repository = SortingRepository()

    fun getNewArray(): ArrayModelToSort? {
        return repository.getNewArray()
    }

    fun getLiveArray(): LiveData<ArrayModelToSort>{
        return repository.getLiveArray()
    }

    fun selectionSort() {
        repository.selectionSort()
    }

}