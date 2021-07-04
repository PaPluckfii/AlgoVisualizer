package com.sumeet.algovisualizer.ui.selection

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.sumeet.algovisualizer.R
import com.sumeet.algovisualizer.ui.MyValueFormatter
import kotlinx.android.synthetic.main.selection_sort_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random


class SelectionSortFragment : Fragment() {

    companion object {
        fun newInstance() = SelectionSortFragment()
    }

    private lateinit var viewModel: SelectionSortViewModel
    private lateinit var dataSet: BarDataSet
    private lateinit var list: MutableList<BarEntry>
    private lateinit var colorList: MutableList<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SelectionSortViewModel::class.java)
        return inflater.inflate(R.layout.selection_sort_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var i = 1f

        list = MutableList(10) {
            BarEntry(
                i++,
                Random.nextInt(1, 20).toFloat()
            )
        }

        colorList = MutableList(10){
            ContextCompat.getColor(requireContext(), R.color.theme_orange)
        }

        dataSet = BarDataSet(
            list,
            "MyDataSet"
        )

        dataSet.colors = colorList


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

        btnStartSelectionSort.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                selectionSort()
            }
        }

    }

    private fun selectionSort(): MutableList<BarEntry> {

        for (i in list.indices) {
            Thread.sleep(1000)
            var min_idx = i
            colorList[min_idx] = ContextCompat.getColor(requireContext(), R.color.black)
            selectionSortChart.invalidate()
            for (j in i + 1 until list.size) {
                colorList[j] = ContextCompat.getColor(requireContext(), R.color.red)
                selectionSortChart.invalidate()
                if (list[j].y < list[min_idx].y) {
                    min_idx = j
                    colorList[i] = ContextCompat.getColor(requireContext(), R.color.theme_orange)
                    colorList[min_idx] = ContextCompat.getColor(requireContext(), R.color.red)
                    selectionSortChart.invalidate()
                }
            }

            colorList[i] = ContextCompat.getColor(requireContext(), R.color.red)
            selectionSortChart.invalidate()

            // Swap the found minimum element with the first
            // element
            val temp = list[min_idx].y

            list[min_idx].y = list[i].y
            list[i].y = temp

            colorList[i] = ContextCompat.getColor(requireContext(), R.color.theme_orange_variant)
            selectionSortChart.invalidate()
//            selectionSortChart.notifyDataSetChanged()
//            val handler = Handler()
//            handler.postDelayed({
//                selectionSortChart.notifyDataSetChanged()
//            }, 3000)

            selectionSortChart.invalidate()
        }

        return list
    }
}
