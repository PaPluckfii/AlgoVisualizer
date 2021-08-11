package com.sumeet.algovisualizer.ui.bottomfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sumeet.algovisualizer.R
import kotlinx.android.synthetic.main.fragment_code_view.*

class CodeViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_code_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        code_view.setCode(getString(R.string.selection_sort_code))

    }

    companion object {
        fun newInstance() = CodeViewFragment()
    }
}