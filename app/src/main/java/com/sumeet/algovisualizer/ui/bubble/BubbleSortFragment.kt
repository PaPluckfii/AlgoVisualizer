package com.sumeet.algovisualizer.ui.bubble

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sumeet.algovisualizer.R

class BubbleSortFragment : Fragment() {

    companion object {
        fun newInstance() = BubbleSortFragment()
    }

    private lateinit var viewModel: BubbleSortViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bubble_sort_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BubbleSortViewModel::class.java)
        // TODO: Use the ViewModel
    }

}