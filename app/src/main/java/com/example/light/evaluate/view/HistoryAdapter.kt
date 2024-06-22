package com.example.light.evaluate.view

import android.util.Log
import com.example.light.evaluate.model.EvaluateResultModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.light.R


class HistoryAdapter(taskList: List<EvaluateResultModel>) :
    RecyclerView.Adapter<HistoryAdapter.HeroViewHolder>() {
    private var task: List<EvaluateResultModel>

    init {
        this.task = taskList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.evaluation_result, parent, false)
        return HeroViewHolder(v)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.HeroViewHolder, position: Int) {
        val hero: EvaluateResultModel = task[position]

        holder.rid.text = hero.rid
        Log.d("work please",hero.rid)
    }



    override fun getItemCount(): Int {
        return task.size
    }

    inner class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rid: TextView

        init {
            rid = itemView.findViewById(R.id.rid)
        }
    }
    fun updateData(newData: List<EvaluateResultModel>) {
        task = newData
        notifyDataSetChanged()
    }

}
