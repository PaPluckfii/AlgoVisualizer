package com.addas.algovisualizer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor() : ViewModel() {
    var data by mutableStateOf(listOf(8, 4, 2, 9, 1, 5, 7, 3, 6))

    fun shuffleData() {
        data = data.shuffled()
    }

    fun sortArr(arr: MutableList<Int>) {
        // Bubble Sort
        CoroutineScope(Dispatchers.IO).launch {
            for (i in arr.indices) {
                for (j in 0 until arr.size - i - 1) {
                    // Highlight the two bars being compared
                    arr[j] = arr[j] * -1
                    arr[j + 1] = arr[j + 1] * -1

                    // Delay for visualization purposes
                    delay(1000)

                    if (arr[j] > arr[j + 1]) {
                        // Swap the two bars
                        val temp = arr[j]
                        arr[j] = arr[j + 1]
                        arr[j + 1] = temp

                        // Delay for visualization purposes
                        delay(1000)
                    }

                    // Un-highlight the two bars
                    arr[j] = arr[j] * -1
                    arr[j + 1] = arr[j + 1] * -1
                }
            }
        }
    }

}