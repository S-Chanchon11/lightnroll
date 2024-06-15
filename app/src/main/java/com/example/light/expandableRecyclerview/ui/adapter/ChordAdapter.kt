package com.example.light.expandableRecyclerview.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.light.R
import com.example.light.expandableRecyclerview.model.RecyclerModel

class ChordAdapter(heroList: List<RecyclerModel>, context: Context) :
    RecyclerView.Adapter<ChordAdapter.HeroViewHolder>() {
    private val heroList: List<RecyclerModel>
    private val context: Context
    private var currentPosition: Int = 0
    private var drawables: Array<Drawable>

    init {
        this.heroList = heroList
        this.context = context
        this.drawables = arrayOf(
            getDrawable(R.drawable.c_maj),
            getDrawable(R.drawable.d_maj),
            getDrawable(R.drawable.e_maj),
            getDrawable(R.drawable.f_maj),
            getDrawable(R.drawable.g_maj),
            getDrawable(R.drawable.a_maj),
            getDrawable(R.drawable.b_maj)
        )
    }
    fun getDrawable(id: Int): Drawable {
        return ResourcesCompat.getDrawable(context.resources, id, context.theme)!!
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.chord_diagram_2, parent, false)
        return HeroViewHolder(v)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: HeroViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val hero: RecyclerModel = heroList[position]

        holder.textViewName.setText(hero.chord)
        holder.imageChord.setImageDrawable(drawables[position])

        if (position != currentPosition) {
            holder.linearLayout.visibility = View.GONE
        }
        holder.textViewName.setOnClickListener {
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
        var imageChord: ImageView
        init {
            textViewName = itemView.findViewById(R.id.title_text2)
            linearLayout = itemView.findViewById(R.id.chord_linearlayout)
            imageChord = itemView.findViewById(R.id.chordImage)
        }
    }
}
