package com.sumeet.algovisualizer.ui.selection

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.sumeet.algovisualizer.R
import com.sumeet.algovisualizer.repository.SortingRepository
import kotlinx.android.synthetic.main.selection_sort_fragment.*
import kotlin.random.Random

class SelectionSortViewModel : ViewModel() {

    val repository = SortingRepository
    private var dataSetList = MutableLiveData<MutableList<BarEntry>>()
    val tempDataSetList: LiveData<MutableList<BarEntry>> get() = dataSetList
    init {
        dataSetList.value = mutableListOf<BarEntry>()
    }



//    fun generateRandomModels(): MutableList<BarDataSet> {
//        list = repository.generateModels()
//        return list
//    }

//    fun observeSelectionSort() : MutableLiveData<MutableList<BarEntry>> {
//        return repository.selectionSort()
//    }

//    val updateUI: MutableList<BarEntry> by lazy {
//        MutableList(10) {
//            BarEntry(
//                Random.nextInt(0, 20).toFloat(),
//                -0xffb300
//            )
//        }
//    }



    fun selectionSort(
        list:MutableList<BarEntry>,
        colorList: MutableList<Int>,
        context: Context
    ): MutableLiveData<MutableList<BarEntry>> {

        for (i in list.indices) {
            Thread.sleep(1000)
            var min_idx = i
            colorList[min_idx] = ContextCompat.getColor(context, R.color.black)

            for (j in i + 1 until list.size) {
                colorList[j] = ContextCompat.getColor(context, R.color.red)

                if (list[j].y < list[min_idx].y) {
                    min_idx = j
                    colorList[i] = ContextCompat.getColor(context, R.color.theme_orange)
                    colorList[min_idx] = ContextCompat.getColor(context, R.color.red)

                }
            }

            colorList[i] = ContextCompat.getColor(context, R.color.red)


            // Swap the found minimum element with the first
            // element
            val temp = list[min_idx].y

            list[min_idx].y = list[i].y
            list[i].y = temp

            colorList[i] = ContextCompat.getColor(context, R.color.theme_orange_variant)

//            selectionSortChart.notifyDataSetChanged()
//            val handler = Handler()
//            handler.postDelayed({
//                selectionSortChart.notifyDataSetChanged()
//            }, 3000)


        }
        return MutableLiveData(list)
    }

}