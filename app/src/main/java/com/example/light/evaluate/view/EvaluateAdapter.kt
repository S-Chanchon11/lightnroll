package com.example.light.evaluate.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.light.R
import com.example.light.evaluate.model.EvaluateSongModel

class EvaluateAdapter(heroList: List<EvaluateSongModel>) :
    RecyclerView.Adapter<EvaluateAdapter.HeroViewHolder>() {
    private val songList: List<EvaluateSongModel>
    private var currentPosition: Int = 0

    init {
        this.songList = heroList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.evaluation_menu_detail, parent, false)
        return HeroViewHolder(v)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: HeroViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val hero: EvaluateSongModel = songList[position]

        holder.songTitleTxt.setText(hero.song_name)
        holder.songTxt.setText(hero.song_name)
        holder.artistTxt.setText(hero.artist)
        holder.chordSampleTxt.setText(hero.chord_sample)
        holder.tempoTxt.setText(hero.tempo.toString())
        holder.keyTxt.setText(hero.key)
        holder.levelTxt.setText(hero.level)

        if (position != currentPosition) {
            holder.linearLayout.visibility = View.GONE
        }
        holder.songTitleTxt.setOnClickListener {
            currentPosition = position
            holder.linearLayout.visibility = View.VISIBLE

            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    inner class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var songTitleTxt: TextView
        var linearLayout: LinearLayout
        var songTxt: TextView
        var artistTxt: TextView
        var chordSampleTxt: TextView
        var tempoTxt: TextView
        var keyTxt: TextView
        var levelTxt: TextView
        init {
            songTitleTxt = itemView.findViewById(R.id.songTitleText)
            linearLayout = itemView.findViewById(R.id.songLinearlayout)
            songTxt = itemView.findViewById(R.id.songNameText)
            artistTxt = itemView.findViewById(R.id.artistNameText)
            chordSampleTxt = itemView.findViewById(R.id.chordSampleText)
            tempoTxt = itemView.findViewById(R.id.tempoText)
            keyTxt = itemView.findViewById(R.id.keyText)
            levelTxt = itemView.findViewById(R.id.levelText)
        }
    }
}
