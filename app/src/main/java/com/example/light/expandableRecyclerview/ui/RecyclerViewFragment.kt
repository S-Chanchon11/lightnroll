package com.example.light.expandableRecyclerview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.light.R
import com.example.light.expandableRecyclerview.model.RecyclerModel
import com.example.light.expandableRecyclerview.ui.adapter.RecyclerViewAdapter
import com.example.light.expandableRecyclerview.viewmodel.RecylerViewViewModel

class RecyclerViewFragment : Fragment() {

    private lateinit var viewModel: RecylerViewViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var heroList: List<RecyclerModel>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recyler_view, container, false)
        recyclerView = view.findViewById(R.id.recyclerview)
        heroList = ArrayList()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RecylerViewViewModel::class.java]
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view?.context)
        heroList = viewModel.loadData()
        adapter = RecyclerViewAdapter(heroList, view.context)
        recyclerView.adapter = adapter
    }
}