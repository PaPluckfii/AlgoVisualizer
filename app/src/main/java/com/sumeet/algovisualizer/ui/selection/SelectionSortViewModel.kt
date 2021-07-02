package com.sumeet.algovisualizer.ui.selection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sumeet.algovisualizer.repository.SortingRepository
import org.eazegraph.lib.models.BarModel
import kotlin.random.Random

class SelectionSortViewModel : ViewModel() {

    val repository = SortingRepository
    lateinit var list: MutableList<BarModel>

    fun generateRandomModels(defaultColor: Int): MutableList<BarModel> {
        list = repository.generateModels(defaultColor)
        return list
    }

    fun observeSelectionSort() : MutableLiveData<MutableList<BarModel>> {
        return repository.selectionSort()
    }

    val updateUI: MutableList<BarModel> by lazy {
        MutableList(10) {
            BarModel(
                Random.nextInt(0, 20).toFloat(),
                -0xffb300
            )
        }
    }

}