package com.example.light.chord.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.light.R
import com.example.light.chord.model.RecyclerModel

class ChordAdapter(chordList: List<RecyclerModel>, context: Context, variant:String?) :
    RecyclerView.Adapter<ChordAdapter.HeroViewHolder>() {
    private val heroList: List<RecyclerModel>
    private val context: Context
    private lateinit var drawables: Array<Drawable>
    private var variant:String?
    init {
        this.heroList = chordList
        this.context = context
        this.variant = variant
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
        when(variant) {
            "Major" -> {
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
            "Minor" -> {
                this.drawables = arrayOf(
                    getDrawable(R.drawable.c_minor),
                    getDrawable(R.drawable.d_minor),
                    getDrawable(R.drawable.e_minor),
                    getDrawable(R.drawable.f_minor),
                    getDrawable(R.drawable.g_minor),
                    getDrawable(R.drawable.a_minor),
                    getDrawable(R.drawable.b_minor)
                )
            }
            "Major7" -> {
                this.drawables = arrayOf(
                    getDrawable(R.drawable.cmaj7),
                    getDrawable(R.drawable.dmaj7),
                    getDrawable(R.drawable.emaj7),
                    getDrawable(R.drawable.fmaj7),
                    getDrawable(R.drawable.gmaj7),
                    getDrawable(R.drawable.amaj7),
                    getDrawable(R.drawable.bmaj7)
                )
            }
        }

        holder.textViewName.setText(hero.chord)
        holder.imageChord.setImageDrawable(drawables[position])
        val isExpandable: Boolean = hero.isExpandable
        holder.imageChord.visibility = if (isExpandable) View.VISIBLE else View.GONE
        holder.constraintLayout.setOnClickListener {
            isAnyItemExpanded(position)
            hero.isExpandable = !hero.isExpandable
            notifyItemChanged(position, Unit)
        }
    }
    private fun isAnyItemExpanded(position: Int) {
        val temp = heroList.indexOfFirst {
            it.isExpandable
        }
        if (temp >= 0 && temp != position) {
            heroList[temp].isExpandable = false
            notifyItemChanged(temp, 0)
        }
    }

    override fun getItemCount(): Int {
        return heroList.size
    }

    inner class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView
        var constraintLayout: ConstraintLayout
        var imageChord: ImageView
        init {
            textViewName = itemView.findViewById(R.id.title_text2)
            constraintLayout = itemView.findViewById(R.id.constraintLayout)
            imageChord = itemView.findViewById(R.id.chordImage)
        }
    }
}
