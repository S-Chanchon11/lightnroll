package com.example.light.practice

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.light.ChordModel
import com.example.light.R

    /*
    In MVC arch, Adapter will act as CONTROLLER
     */
class PracticeAdapterController(data: MutableList<ChordModel>) :
    RecyclerView.Adapter<PracticeAdapterController.ViewHolder>() {
    private val dataList: MutableList<ChordModel>

    init {
        dataList = data
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewText: TextView

        init {
            textViewText = itemView.findViewById<View>(R.id.chordName) as TextView
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.chord_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewText.text = dataList[position].chord
        Log.d(javaClass.toString(), dataList[position].chord)
        holder.itemView.setOnClickListener {
//            Toast.makeText(holder,
//                "Item $position is clicked.",
//                Toast.LENGTH_SHORT
//            ).show()
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
