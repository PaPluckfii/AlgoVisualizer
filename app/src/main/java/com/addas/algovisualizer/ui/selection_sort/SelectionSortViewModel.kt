package com.addas.algovisualizer.ui.selection_sort

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.CopyOnWriteArrayList
import javax.inject.Inject

@HiltViewModel
class SelectionSortViewModel @Inject constructor(@ApplicationContext context: Context) :
    ViewModel() {

    var array = CopyOnWriteArrayList<Int>()

    var speed = mutableStateOf(0.1F)

    var arraySize = mutableStateOf(10)
    var minRange = mutableStateOf(1)
    var maxRange = mutableStateOf(100)
    var currentIndex = mutableStateOf(-1)
    var currentSubIndex = mutableStateOf(-1)
    var minimumIndex = mutableStateOf(-1)

    var runningStatus = mutableStateOf(false)

    val toneGenerator = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100)

    fun generateArray() {
        array.clear()
        repeat(arraySize.value) {
            array.add((minRange.value..maxRange.value).random())
        }
    }

    fun activateKillSwitch() {
        sortingJob?.cancel()
        runningStatus.value = false
    }

    fun resetArray() {
        activateKillSwitch()
        generateArray()
        resetIndexes()
    }

    private fun resetIndexes() {
        currentIndex.value = -1
        currentSubIndex.value = -1
        minimumIndex.value = -1
    }

    private var sortingJob: Job? = null

    val TAG = "SELECTIONSort"
    fun selectionSort() {
        sortingJob = viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "selectionSort: Starting Sort")
            runningStatus.value = true
            val lastIndex: Int = array.size - 1

            for (i in 0 until lastIndex) {
                Log.d(TAG, "selectionSort: Current Index = $i")
                currentIndex.value = i
                var minIndex = i

                for (j in (i + 1)..lastIndex) {
                    Log.d(TAG, "selectionSort: Current SubIndex = $j")
                    currentSubIndex.value = j
                    if (array[minIndex] > array[j]) {   //finding minimum
                        Log.d(TAG, "selectionSort: Found Minimum at index = $j")
                        minimumIndex.value = j
                        minIndex = j
                    }
                    delay(speed.value.getDelayTimer())
                    Log.d(TAG, "selectionSort: Pause right here")
                }

                val temp = array[minIndex]  //value swap
                array[minIndex] = array[i]
                array[i] = temp
                currentIndex.value = minIndex
                minimumIndex.value = i

                toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP) //creating beep sound on value swap
                delay(speed.value.getDelayTimer())
                toneGenerator.stopTone()

            }
            runningStatus.value = false
            resetIndexes()
        }
    }

    fun Float.getDelayTimer(): Long = (this * 100).toLong()

    override fun onCleared() {
        super.onCleared()
        toneGenerator.release()
    }

}