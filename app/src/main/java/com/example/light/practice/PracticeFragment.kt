package com.example.light.practice


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.light.Chord
import com.example.light.R
import com.example.light.practice.PracticeController.loadData


class PracticeFragment : Fragment() {
    private var mTextViewEmpty: TextView? = null
    private var mProgressBarLoading: ProgressBar? = null
    private var mRecyclerView: RecyclerView? = null
    private var mListadapter: PracticeAdapter? = null
    private var pController : PracticeController? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_practice, container, false)
        mRecyclerView = view.findViewById<View>(R.id.recyclerView) as RecyclerView
        mTextViewEmpty = view.findViewById<View>(R.id.textViewEmpty) as TextView
        mProgressBarLoading = view.findViewById<View>(R.id.progressBarLoading) as ProgressBar
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        mRecyclerView!!.layoutManager = layoutManager
        val chordC = Chord("C")
        val data: ArrayList<Chord> = ArrayList()
        data.add(chordC)

        mListadapter = PracticeAdapter(data)
        mRecyclerView!!.adapter = mListadapter
        loadData()


        return view
    }

//    inner class ListAdapter(data: ArrayList<Chord>) :
//        RecyclerView.Adapter<ListAdapter.ViewHolder>() {
//        private val dataList: ArrayList<Chord>
//
//        init {
//            dataList = data
//        }
//
//        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//            var textViewText: TextView
//
//
//            init {
//                textViewText = itemView.findViewById<View>(R.id.chordName) as TextView
//
//            }
//        }
//
//        override fun onCreateViewHolder(
//            parent: ViewGroup,
//            viewType: Int
//        ): ViewHolder {
//            val view: View = LayoutInflater.from(parent.context)
//                .inflate(R.layout.chord_list, parent, false)
//            return ViewHolder(view)
//        }
//
//        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//            holder.textViewText.text = dataList[position].chord
//            Log.d(javaClass.toString(), dataList[position].chord)
//            holder.itemView.setOnClickListener {
//                Toast.makeText(
//                    activity,
//                    "Item $position is clicked.",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//
//        override fun getItemCount(): Int {
//            return dataList.size
//        }
//    }
}