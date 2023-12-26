package com.example.light.expandableRecyclerview.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.light.R
import com.example.light.expandableRecyclerview.model.RecyclerModel

class RecyclerViewAdapter(heroList: List<RecyclerModel>, context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.HeroViewHolder>() {
    private val heroList: List<RecyclerModel>
    private val context: Context
    private var currentPosition: Int = 0

    init {
        this.heroList = heroList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.chord_diagram, parent, false)
        return HeroViewHolder(v)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val hero: RecyclerModel = heroList[position]
        val pos6: String = hero.positions.s6
        val pos5: String = hero.positions.s5
        val pos4: String = hero.positions.s4
        val pos3: String = hero.positions.s3
        val pos2: String = hero.positions.s2
        val pos1: String = hero.positions.s1
        val fin6: String = hero.fingerings.f6
        val fin5: String = hero.fingerings.f5
        val fin4: String = hero.fingerings.f4
        val fin3: String = hero.fingerings.f3
        val fin2: String = hero.fingerings.f2
        val fin1: String = hero.fingerings.f1

        holder.textViewName.setText(hero.chord)
        if (position != currentPosition) {
            holder.linearLayout.visibility = View.GONE
        }
        holder.textViewName.setOnClickListener {
            currentPosition = position
            holder.linearLayout.visibility = View.VISIBLE
            holder.textViewName1[fin6.toInt()].text = pos6
            holder.textViewName2[fin5.toInt()].text = pos5
            holder.textViewName3[fin4.toInt()].text = pos4
            holder.textViewName4[fin3.toInt()].text = pos3
            holder.textViewName5[fin2.toInt()].text = pos2
            holder.textViewName6[fin1.toInt()].text = pos1

            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return heroList.size
    }

    inner class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView
        var linearLayout: LinearLayout
        var textViewName1: ArrayList<TextView>
        var textViewName2: ArrayList<TextView>
        var textViewName3: ArrayList<TextView>
        var textViewName4: ArrayList<TextView>
        var textViewName5: ArrayList<TextView>
        var textViewName6: ArrayList<TextView>
        init {
            textViewName = itemView.findViewById(R.id.title_text2)
            linearLayout = itemView.findViewById(R.id.chord_linearlayout)
            textViewName1 = ArrayList()
            textViewName2 = ArrayList()
            textViewName3 = ArrayList()
            textViewName4 = ArrayList()
            textViewName5 = ArrayList()
            textViewName6 = ArrayList()
            textViewName1.add(itemView.findViewById(R.id.s61))
            textViewName1.add(itemView.findViewById(R.id.s62))
            textViewName1.add(itemView.findViewById(R.id.s63))
            textViewName1.add(itemView.findViewById(R.id.s64))
            textViewName1.add(itemView.findViewById(R.id.s65))
            textViewName2.add(itemView.findViewById(R.id.s51))
            textViewName2.add(itemView.findViewById(R.id.s52))
            textViewName2.add(itemView.findViewById(R.id.s53))
            textViewName2.add(itemView.findViewById(R.id.s54))
            textViewName2.add(itemView.findViewById(R.id.s55))
            textViewName3.add(itemView.findViewById(R.id.s41))
            textViewName3.add(itemView.findViewById(R.id.s42))
            textViewName3.add(itemView.findViewById(R.id.s43))
            textViewName3.add(itemView.findViewById(R.id.s44))
            textViewName3.add(itemView.findViewById(R.id.s45))
            textViewName4.add(itemView.findViewById(R.id.s31))
            textViewName4.add(itemView.findViewById(R.id.s32))
            textViewName4.add(itemView.findViewById(R.id.s33))
            textViewName4.add(itemView.findViewById(R.id.s34))
            textViewName4.add(itemView.findViewById(R.id.s35))
            textViewName5.add(itemView.findViewById(R.id.s21))
            textViewName5.add(itemView.findViewById(R.id.s22))
            textViewName5.add(itemView.findViewById(R.id.s23))
            textViewName5.add(itemView.findViewById(R.id.s24))
            textViewName5.add(itemView.findViewById(R.id.s25))
            textViewName6.add(itemView.findViewById(R.id.s11))
            textViewName6.add(itemView.findViewById(R.id.s12))
            textViewName6.add(itemView.findViewById(R.id.s13))
            textViewName6.add(itemView.findViewById(R.id.s14))
            textViewName6.add(itemView.findViewById(R.id.s15))
        }
    }
}
