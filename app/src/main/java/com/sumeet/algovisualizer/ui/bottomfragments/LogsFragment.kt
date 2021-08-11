package com.sumeet.algovisualizer.ui.bottomfragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sumeet.algovisualizer.R
import com.sumeet.algovisualizer.model.room.LogsEntity
import com.sumeet.algovisualizer.ui.adapters.LogsRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_logs.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LogsFragment : Fragment() {

    private lateinit var logsRecyclerAdapter: LogsRecyclerAdapter
    private lateinit var viewModel : LogsViewModel
    private var logs : List<LogsEntity> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(LogsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_logs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logsRecyclerAdapter = LogsRecyclerAdapter(logs)
        logsRecyclerView.layoutManager = LinearLayoutManager(context)
        logsRecyclerView.adapter = logsRecyclerAdapter

        CoroutineScope(Dispatchers.IO).launch {
            checkFlowData()
        }

    }

    private suspend fun checkFlowData(){
        viewModel.getLiveLogs().collect {
            logs = it
            withContext(Dispatchers.Main){
                logsRecyclerAdapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        fun newInstance() = LogsFragment()
    }
}