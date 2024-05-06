package com.addas.algovisualizer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    val array = mutableStateListOf<Int>()
    val currentArraySize = mutableStateOf(10)
    val minRange = mutableStateOf(0)
    val maxRange = mutableStateOf(10)

    fun resetArray() {
        array.clear()
        repeat(currentArraySize.value) {
            array.add(Random.nextInt(minRange.value, maxRange.value))
        }
    }

}