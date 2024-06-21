package com.example.light.evaluate.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.light.R
import com.example.light.evaluate.model.EvaluateSongModel
import com.unity3d.player.UnityPlayerActivity

class EvaluateAdapter(heroList: List<EvaluateSongModel>, context: Context) :
    RecyclerView.Adapter<EvaluateAdapter.HeroViewHolder>() {
    private val songList: List<EvaluateSongModel>
    private var currentPosition: Int = 0
    private var context: Context

    init {
        this.songList = heroList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.evaluation_menu_detail, parent, false)
        return HeroViewHolder(v)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: HeroViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val hero: EvaluateSongModel = songList[position]

        holder.songTitleTxt.text = hero.song_name
        holder.songTxt.text = "Song: " + hero.song_name
        holder.artistTxt.text = "Artist: " + hero.artist
        holder.chordSampleTxt.text = "Chords: " + hero.chord_sample
        holder.tempoTxt.text = "Tempo: " + hero.tempo.toString()
        holder.keyTxt.text = "Key: " + hero.key
        holder.levelTxt.text = "Level: " + hero.level
        holder.playBtn.setOnClickListener {
            gotoRhythm(hero.song_name)
        }
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
        var playBtn: Button
        init {
            songTitleTxt = itemView.findViewById(R.id.songTitleText)
            linearLayout = itemView.findViewById(R.id.songLinearlayout)
            songTxt = itemView.findViewById(R.id.songNameText)
            artistTxt = itemView.findViewById(R.id.artistNameText)
            chordSampleTxt = itemView.findViewById(R.id.chordSampleText)
            tempoTxt = itemView.findViewById(R.id.tempoText)
            keyTxt = itemView.findViewById(R.id.keyText)
            levelTxt = itemView.findViewById(R.id.levelText)
            playBtn = itemView.findViewById(R.id.playButton)
        }
    }
    private fun gotoRhythm(choice: String) {
        val intent = Intent(context, UnityPlayerActivity::class.java)
        intent.putExtra("name", choice)
        context.startActivity(intent)
    }
}
