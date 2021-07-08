package com.sumeet.algovisualizer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.github.mikephil.charting.data.BarEntry
import com.sumeet.algovisualizer.R
import com.sumeet.algovisualizer.model.ArrayModelToSort
import kotlin.random.Random

class SortingRepository {

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
               R.color.theme_orange
            }
        ))
        return arrayModelToSort.value
    }

    fun getLiveArray(): MutableLiveData<ArrayModelToSort>{
        return arrayModelToSort
    }

    fun selectionSort(){

        for (i in arrayModelToSort.value?.array?.indices!!) {
            //Thread.sleep(1000)
            var minIndex = i
            arrayModelToSort.value!!.colorList[minIndex] = R.color.purple_700

            for (j in i + 1 until arrayModelToSort.value!!.array.size) {

                //Thread.sleep(500)
                if (arrayModelToSort.value!!.array[j].y < arrayModelToSort.value!!.array[minIndex].y) {

                    minIndex = j
                    arrayModelToSort.value!!.colorList[i] = R.color.theme_orange
                    arrayModelToSort.value!!.colorList[j] = R.color.red

                }

            }

            val temp = arrayModelToSort.value!!.array[minIndex].y
            arrayModelToSort.value!!.colorList[minIndex]

            arrayModelToSort.value!!.array[minIndex].y = arrayModelToSort.value!!.array[i].y
            arrayModelToSort.value!!.array[i].y = temp

        }
    }

}