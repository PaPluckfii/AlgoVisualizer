package com.sumeet.algovisualizer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sumeet.algovisualizer.model.SelectionSort
import org.eazegraph.lib.models.BarModel
import kotlin.random.Random

object SortingRepository {

    private lateinit var barModelList: MutableList<BarModel>

    fun generateModels(defaultColor: Int): MutableList<BarModel> {
        barModelList =  MutableList(10) {
            BarModel(
                Random.nextInt(1, 20).toFloat(),
                defaultColor
            )
        }
        return barModelList
    }

    fun selectionSort(): MutableLiveData<MutableList<BarModel>> {
        val selectionSort = SelectionSort(barModelList)
        selectionSort.sortOneStep()
        barModelList = selectionSort.list
        return MutableLiveData(barModelList)
    }

}