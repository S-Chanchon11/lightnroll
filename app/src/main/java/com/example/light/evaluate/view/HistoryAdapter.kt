package com.example.light.evaluate.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.light.R
import com.example.light.evaluate.model.EvaluateResultModel

class HistoryAdapter(taskList: List<EvaluateResultModel>, context: Context) :
    RecyclerView.Adapter<HistoryAdapter.HeroViewHolder>() {
    private var task: List<EvaluateResultModel>
    val TAG = "HistoryAdapter"
    val context: Context
    init {
        this.task = taskList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.evaluation_result, parent, false)
        return HeroViewHolder(v)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: HistoryAdapter.HeroViewHolder, position: Int) {
        val hero: EvaluateResultModel = task[position]

        holder.rid.text = hero.song_name
        var actualTempo:Int = 0
        var acutalChord:List<String> = listOf()

        Log.d("his adapter", holder.rid.text.toString())
        if(hero.tempo == 0){
            holder.scoreTxt.text = "off beat"
            holder.scoreTxt.setTextColor(Color.parseColor("#FF0000"))

        } else {

            holder.scoreTxt.text = hero.score.toString() + "/100"
        }



        holder.icon.setOnClickListener {
            when(hero.song_name){
                "Photograph"   -> {
                    acutalChord = listOf("C","C","D","D")
                    actualTempo=108
                }
                "All of Me "   -> {
                    acutalChord = listOf("Em","C","G","D","Em","C","G","D","Em","C","G")
                    actualTempo=120
                }
                "Let Her Go"    -> {
                    acutalChord = listOf("C","D","Em","D","C","D","Em","D")
                    actualTempo=75
                }
                "Someone You Loved"   -> {
                    acutalChord = listOf("C","G","Am","F")
                    actualTempo=110
                }
            }
            showModal(
                context,
                hero.rid,
                actualTempo,
                hero.tempo,
                hero.score,
                acutalChord,
                hero.pred_result)
        }
    }

    override fun getItemCount(): Int {
        return task.size
    }

    inner class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rid: TextView
        var scoreTxt: TextView
        var icon: ImageView

        init {
            rid = itemView.findViewById(R.id.rid)
            scoreTxt = itemView.findViewById(R.id.scoreTxt)
            icon = itemView.findViewById(R.id.showDetailIcon)
        }
    }
    fun updateData(newData: List<EvaluateResultModel>) {
        task = newData
        notifyDataSetChanged()
    }
    private fun showModal(context: Context,rid:String, tempo:Int, detectedTempo:Int,score: Double, actualChord:List<String>,prediction:List<String>) {
        val dialog = Dialog(context)
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.modal_layout, null)

        var ridTxt :TextView = view.findViewById(R.id.resultID)
        var scoreTxt :TextView = view.findViewById(R.id.scoreText)
        var tempoTxt :TextView = view.findViewById(R.id.tempoText)
        var detectedTempoTxt :TextView = view.findViewById(R.id.detectedTempoText)
        var chordTxt :TextView = view.findViewById(R.id.chordText)
        var detectedChordTxt :TextView = view.findViewById(R.id.detectedChordText)

        ridTxt.text = "ID: $rid"
        scoreTxt.text = score.toString()
        tempoTxt.text = tempo.toString()
        detectedTempoTxt.text = detectedTempo.toString()
        chordTxt.text = actualChord.toString()
        detectedChordTxt.text = prediction.toString()

        dialog.setContentView(view)
        dialog.setCancelable(true) // Allows closing the dialog when touching outside
        dialog.setCanceledOnTouchOutside(true) // Close the dialog when the user touches outside
        dialog.show()
        dialog.window
            ?.setLayout(
                800,
                1500
            );
    }

}
