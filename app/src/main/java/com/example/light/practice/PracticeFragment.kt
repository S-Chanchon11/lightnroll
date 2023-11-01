package com.example.light.practice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.light.ChordModel
import com.example.light.R

class PracticeFragment : Fragment() {

    private var mRecyclerView: RecyclerView? = null
    private var mListadapter: PracticeAdapterController? = null

    /*
    In MVC arch, Fragment will act as VIEW
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_practice, container, false)
        mRecyclerView = view.findViewById<View>(R.id.recyclerView) as RecyclerView
        val layoutManager = GridLayoutManager(activity, 2)
        mRecyclerView!!.layoutManager = layoutManager
        val data: ArrayList<ChordModel> = ArrayList()
        data.add(ChordModel("C"))
        data.add(ChordModel("D"))
        data.add(ChordModel("E"))
        data.add(ChordModel("F"))
        data.add(ChordModel("G"))
        data.add(ChordModel("A"))
        data.add(ChordModel("B"))
        mListadapter = PracticeAdapterController(data)
        mRecyclerView!!.adapter = mListadapter

        return view
    }
}
