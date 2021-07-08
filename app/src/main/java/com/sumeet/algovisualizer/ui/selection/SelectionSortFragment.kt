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

    companion object {
        fun newInstance() = SelectionSortFragment()
    }

    private lateinit var viewModel: SelectionSortViewModel
    private lateinit var myArray: MutableList<BarEntry>
    private lateinit var dataSet: BarDataSet
    private lateinit var colorList: MutableList<Int>
    private lateinit var handlerThread: Handler

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

        viewModel.getLiveArray().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            myArray = it.array
            colorList = it.colorList
            selectionSortChart.invalidate()
        })

        btnStartSelectionSort.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.selectionSort()
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


}
