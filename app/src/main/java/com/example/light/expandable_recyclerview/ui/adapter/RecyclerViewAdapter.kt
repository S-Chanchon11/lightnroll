package com.example.light.expandable_recyclerview.ui.adapter


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.light.R
import com.example.light.expandable_recyclerview.model.RecyclerModel


class RecyclerViewAdapter(heroList: List<RecyclerModel>, context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.HeroViewHolder>() {
    private val heroList: List<RecyclerModel>
    private val context: Context
    private var currentPosition : Int = 0

    init {
        this.heroList = heroList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_detail, parent, false)
        return HeroViewHolder(v)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val hero: RecyclerModel = heroList[position]
        holder.textViewName.setText(hero.chord)

        if(position!=currentPosition){
            holder.linearLayout.visibility = View.GONE
        }
        holder.textViewName.setOnClickListener { //getting the position of the item to expand it
            currentPosition = position
            holder.linearLayout.visibility = View.VISIBLE
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return heroList.size
    }

    inner class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView
        var linearLayout: LinearLayout

        init {
            textViewName = itemView.findViewById(R.id.title_text)
            linearLayout = itemView.findViewById(R.id.expand_linearlayout)
        }
    }
}