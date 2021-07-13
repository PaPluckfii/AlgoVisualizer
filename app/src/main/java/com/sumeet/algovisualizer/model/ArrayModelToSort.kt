package com.sumeet.algovisualizer.model

import com.github.mikephil.charting.data.BarEntry

data class ArrayModelToSort(
    var array : MutableList<BarEntry>,
    var colorList : MutableList<Int>
)