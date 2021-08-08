package com.sumeet.algovisualizer.ui.selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.sumeet.algovisualizer.R
import com.sumeet.algovisualizer.ui.MyValueFormatter
import kotlinx.android.synthetic.main.selection_sort_fragment.*
import kotlinx.coroutines.*

class SelectionSortFragment : Fragment() {

    private lateinit var viewModel: SelectionSortViewModel
    private lateinit var myArray: MutableList<BarEntry>
    private lateinit var dataSet: BarDataSet
    private lateinit var colorList: MutableList<Int>
    private lateinit var selectionSortJob : Job
    private var timer : Long = 800
    private var action = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(SelectionSortViewModel::class.java)

        return inflater.inflate(R.layout.selection_sort_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //getting random num array
        getNewRandomArray()
        //setting up the graph
        setGraphView()
        //setting up the Bottom ViewPager
        setViewPager()
        //handling clicks
        handleListeners()

    }

    /**
     * This function handles clicks
     */
    private fun handleListeners() {
        btnStartSelectionSort.setOnClickListener {
            when (action) {
                0 -> {
                    selectionSortJob = CoroutineScope(Dispatchers.IO).launch {
                        selectionSort()
                    }
                    action = 1
                    btnStartSelectionSort.setImageResource(R.drawable.ic_baseline_stop_24)
                }
                1 -> {
                    action = 2
                    selectionSortJob.cancel()
                    btnStartSelectionSort.setImageResource(R.drawable.ic_baseline_refresh_24)
                }
                else -> {
                    findNavController().navigate(R.id.action_nav_selection_self)
                }
            }
        }
    }

    /**
     * This function generates new Array to be sorted.
     */
    private fun getNewRandomArray(){
        myArray = viewModel.getNewArray()?.array!!
        val list = viewModel.getNewArray()?.colorList
        colorList = MutableList(10){
            ContextCompat.getColor(requireContext(), list?.get(it)!!)
        }
    }

    /**
     * This function is to setup the graph up with the new dataset obtained from getNewRandomArray().
     */
    private fun setGraphView(){
        dataSet = BarDataSet(
            myArray,
            "MyDataSet"
        )

        dataSet.colors = colorList

        val barData = BarData()
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

    private fun setViewPager() {

    }

    /**
     * This function updates the graph on the Main thread
     */
    private suspend fun updateGraph(){
        withContext(Dispatchers.Main) {
            selectionSortChart.invalidate()
        }
    }

    /**
     * This function runs the selection sort.
     */
    private suspend fun selectionSort(){

        for (i in myArray.indices) {

            colorList[i] = ContextCompat.getColor(requireContext(), R.color.theme_orange)
            updateGraph()

            delay(timer)
            var minIndex = i
            updateGraph()

            for (j in i + 1 until myArray.size) {
                colorList[j] = ContextCompat.getColor(requireContext(), R.color.theme_orange)
                selectionSortChart.invalidate()
                delay(timer)
                if (myArray[j].y < myArray[minIndex].y) {

                    if(minIndex != i)
                        colorList[minIndex] = ContextCompat.getColor(requireContext(), R.color.theme_orange_variant)
                    minIndex = j
                    colorList[minIndex] = ContextCompat.getColor(requireContext(), R.color.green)

                    updateGraph()

                }
                if(minIndex != j)
                    colorList[j] = ContextCompat.getColor(requireContext(), R.color.theme_orange_variant)

                updateGraph()
            }

            //swapping values
            val temp = myArray[minIndex].y
            myArray[minIndex].y = myArray[i].y
            myArray[i].y = temp

            colorList[i] = ContextCompat.getColor(requireContext(),R.color.green)
            colorList[minIndex] = ContextCompat.getColor(requireContext(),R.color.theme_orange)
            updateGraph()

            delay(200L)

            //swapping color
            colorList[minIndex] = ContextCompat.getColor(requireContext(), R.color.theme_orange_variant)
            colorList[i] = ContextCompat.getColor(requireContext(), R.color.theme_orange_variant)

            delay(timer)
            updateGraph()
        }
        updateGraph()
        updateActionButton()
    }

    /**
     * Function to update action button on finish of selection sort
     */
    private suspend fun updateActionButton() {
        withContext(Dispatchers.Main){
            btnStartSelectionSort.setImageResource(R.drawable.ic_baseline_refresh_24)
            action = 2
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(selectionSortJob.isActive)
            selectionSortJob.cancel()
    }

}
