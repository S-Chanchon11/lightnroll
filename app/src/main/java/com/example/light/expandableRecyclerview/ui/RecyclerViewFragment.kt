package com.example.light.expandableRecyclerview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.light.R
import com.example.light.expandableRecyclerview.model.RecyclerModel
import com.example.light.expandableRecyclerview.ui.adapter.RecyclerViewAdapter
import com.example.light.expandableRecyclerview.viewmodel.RecylerViewViewModel
import com.example.light.utilities.SpaceItemDecoration

class RecyclerViewFragment : Fragment() {

    private lateinit var viewModel: RecylerViewViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
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

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RecylerViewViewModel::class.java]
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view?.context)
        heroList = viewModel.loadData() // chord data
        adapter = RecyclerViewAdapter(heroList, view.context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(SpaceItemDecoration(32))

        setFragmentResultListener("requestKey") { requestKey, bundle ->
            resultFromFragment = bundle.getString("bundleKey")
            Toast.makeText(context?.applicationContext, resultFromFragment, Toast.LENGTH_SHORT).show()
        }

//        when(resultFromFragment){
//
//        }
    }
}
