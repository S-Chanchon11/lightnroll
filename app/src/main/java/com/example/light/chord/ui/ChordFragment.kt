package com.example.light.chord.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.light.R
import com.example.light.chord.model.RecyclerModel
import com.example.light.chord.ui.adapter.ChordAdapter
import com.example.light.chord.viewmodel.ChordViewModel
import com.example.light.utilities.SpaceItemDecoration

class ChordFragment : Fragment() {

    private lateinit var viewModel: ChordViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ChordAdapter
    private lateinit var heroList: List<RecyclerModel>
    private var resultFromFragment: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recyler_view, container, false)
        recyclerView = view.findViewById(R.id.recyclerview)
        heroList = ArrayList()
        viewModel = ViewModelProvider(this)[ChordViewModel::class.java]
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        heroList = viewModel.loadData() // chord data
        adapter = ChordAdapter(heroList, view.context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(SpaceItemDecoration(24))
        activity?.actionBar?.hide()
        if (activity?.actionBar?.isShowing == true) {
//            Toast.makeText(context?.applicationContext, "showing actionbar", Toast.LENGTH_SHORT).show()
        } else {
//            Toast.makeText(context?.applicationContext, "hiding actionbar", Toast.LENGTH_SHORT).show()
        }
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            resultFromFragment = bundle.getString("bundleKey")
//            Toast.makeText(context?.applicationContext, resultFromFragment, Toast.LENGTH_SHORT).show()
        }
    }
}
