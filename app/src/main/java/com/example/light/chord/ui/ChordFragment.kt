package com.example.light.chord.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
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
import com.google.android.material.bottomnavigation.BottomNavigationView

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
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar.visibility = View.VISIBLE

//        activity?.actionBar?.hide()
        if (activity?.actionBar?.isShowing == true) {
//            Toast.makeText(context?.applicationContext, "showing actionbar", Toast.LENGTH_SHORT).show()
        } else {
//            Toast.makeText(context?.applicationContext, "hiding actionbar", Toast.LENGTH_SHORT).show()
        }
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            resultFromFragment = bundle.getString("bundleKey")
            when(resultFromFragment){
                "Major" -> heroList = viewModel.loadDataMajor()
                "Minor" -> heroList = viewModel.loadDataMinor()
                "Major7" -> heroList = viewModel.loadDataMajor7()
            }
            adapter = ChordAdapter(heroList, view.context,resultFromFragment)
            recyclerView.adapter = adapter
            recyclerView.addItemDecoration(SpaceItemDecoration(24))
//            Toast.makeText(context?.applicationContext, resultFromFragment, Toast.LENGTH_SHORT).show()
        }

        requireActivity().onBackPressedDispatcher.addCallback {
            parentFragmentManager.popBackStack()
        }
    }


}
