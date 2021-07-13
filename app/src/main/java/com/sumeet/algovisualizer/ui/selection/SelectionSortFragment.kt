package com.sumeet.algovisualizer.ui.selection

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.sumeet.algovisualizer.R
import com.sumeet.algovisualizer.ui.MyValueFormatter
import kotlinx.android.synthetic.main.selection_sort_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SelectionSortFragment : Fragment() {

    private lateinit var viewModel: SelectionSortViewModel
    private lateinit var myArray: MutableList<BarEntry>
    private lateinit var dataSet: BarDataSet
    private lateinit var colorList: MutableList<Int>
    private var timer : Long = 800
    private var action = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(SelectionSortViewModel::class.java)


        //Creating new Array when fragment is launched
        myArray = viewModel.getNewArray()?.array!!
        val list = viewModel.getNewArray()?.colorList
        colorList = MutableList(10){
            ContextCompat.getColor(requireContext(), list?.get(it)!!)
        }

        return inflater.inflate(R.layout.selection_sort_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataSet = BarDataSet(
            myArray,
            "MyDataSet"
        )

        dataSet.colors = colorList

        setGraphView()

        btnStartSelectionSort.setOnClickListener {
            if(action == 0){
                CoroutineScope(Dispatchers.IO).launch {
                    selectionSort()
                }
                action = 1
                btnStartSelectionSort.setImageResource(R.drawable.ic_baseline_refresh_24)
            }else{
                val navController = findNavController()
                navController.navigate(R.id.action_nav_selection_self)
            }
        }

    }

    private fun setGraphView(){
        var barData = BarData()
        dataSet.valueTextSize = 20F
        barData.addDataSet(dataSet)

        val valueFormatter = MyValueFormatter()
        barData.setValueFormatter(valueFormatter)
        selectionSortChart.data = barData

        val legend = selectionSortChart.legend
        legend.isEnabled = false
        selectionSortChart.setDrawValueAboveBar(true)

        selectionSortChart.axisRight.setDrawGridLines(false)
        selectionSortChart.axisLeft.setDrawGridLines(false)
        selectionSortChart.xAxis.setDrawGridLines(false)

        selectionSortChart.axisLeft.setDrawLabels(false)
        selectionSortChart.axisRight.setDrawLabels(false)
        selectionSortChart.xAxis.setDrawLabels(false)

        selectionSortChart.legend.isEnabled = false
        selectionSortChart.description.isEnabled = false
        selectionSortChart.setTouchEnabled(false)

        selectionSortChart.invalidate()
    }

    private fun selectionSort(){

        for (i in myArray.indices) {

            colorList[i] = ContextCompat.getColor(requireContext(), R.color.red)
            selectionSortChart.invalidate()

            Thread.sleep(timer)
            var minIndex = i

            colorList[i] = ContextCompat.getColor(requireContext(), R.color.purple_500)
            selectionSortChart.invalidate()

            for (j in i + 1 until myArray.size) {
                colorList[j] = ContextCompat.getColor(requireContext(), R.color.red)
                selectionSortChart.invalidate()
                Thread.sleep(timer)
                if (myArray[j].y < myArray[minIndex].y) {

                    if(minIndex != i)
                        colorList[minIndex] = ContextCompat.getColor(requireContext(), R.color.theme_orange)
                    minIndex = j
                    colorList[minIndex] = ContextCompat.getColor(requireContext(), R.color.green)
                    selectionSortChart.invalidate()

                }
                if(minIndex != j)
                    colorList[j] = ContextCompat.getColor(requireContext(), R.color.theme_orange)
                selectionSortChart.invalidate()
            }

            selectionSortChart.invalidate()

            val temp = myArray[minIndex].y
            myArray[minIndex].y = myArray[i].y
            myArray[i].y = temp

            Thread.sleep(300)
            colorList[minIndex] = ContextCompat.getColor(requireContext(), R.color.theme_orange)
            colorList[i] = ContextCompat.getColor(requireContext(), R.color.theme_orange)
            selectionSortChart.invalidate()
        }

        selectionSortChart.invalidate()

    }

}
