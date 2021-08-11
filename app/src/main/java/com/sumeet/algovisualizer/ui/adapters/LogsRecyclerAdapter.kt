package com.sumeet.algovisualizer.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumeet.algovisualizer.R
import com.sumeet.algovisualizer.model.room.LogsEntity
import kotlinx.android.synthetic.main.item_logs_layout.view.*

class LogsRecyclerAdapter(
    private val list: List<LogsEntity>
) : RecyclerView.Adapter<LogsRecyclerAdapter.LogsViewHolder>(){

    inner class LogsViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun setData(logsEntity: LogsEntity){
            itemView.tvLogs.text = logsEntity.log
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_logs_layout,parent,false)
        return LogsViewHolder(view)
    }

    override fun onBindViewHolder(holder: LogsViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}