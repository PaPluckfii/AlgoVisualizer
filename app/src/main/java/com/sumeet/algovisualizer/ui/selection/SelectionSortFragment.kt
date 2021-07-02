package com.sumeet.algovisualizer.ui.selection

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sumeet.algovisualizer.R
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.selection_sort_fragment.*
import org.eazegraph.lib.models.BarModel


class SelectionSortFragment : Fragment() {

    companion object {
        fun newInstance() = SelectionSortFragment()
    }

    private lateinit var viewModel: SelectionSortViewModel

    private val defaultColor:Int = -0xffb300

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SelectionSortViewModel::class.java)
        return inflater.inflate(R.layout.selection_sort_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectionSortChart.animationTime = 500
        selectionSortChart.addBarList(viewModel.generateRandomModels(defaultColor))

        selectionSortChart.startAnimation()

        btnStartSelectionSort.setOnClickListener {
            viewModel.observeSelectionSort().observe(viewLifecycleOwner, Observer {
                selectionSortChart.clearChart()
                selectionSortChart.addBarList(it)
            })
        }

//        Handler().postDelayed({
//            list[0].value = 2f
//            selectionSortChart.animationTime = 5
//            selectionSortChart.startAnimation()
//            selectionSortChart.refreshDrawableState()
//            Toast.makeText(context,"Now",Toast.LENGTH_LONG).show()
//        },10000)

    }

}