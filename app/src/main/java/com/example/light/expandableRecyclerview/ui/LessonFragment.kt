package com.example.light.expandableRecyclerview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.light.R
import com.example.light.expandableRecyclerview.model.LessonModel
import com.example.light.expandableRecyclerview.ui.adapter.LessonAdapter
import com.example.light.expandableRecyclerview.viewmodel.LessonViewModel

class LessonFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var lessonList: List<LessonModel>
    private lateinit var viewModel: LessonViewModel
    private lateinit var adapter: LessonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recyler_view, container, false)
        recyclerView = view.findViewById(R.id.recyclerview)

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LessonViewModel::class.java]
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view?.context)
        lessonList = viewModel.loadLessonData()
        adapter = LessonAdapter(lessonList, view.context)
        recyclerView.adapter = adapter
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
//            var item.itemId
        }
        return true
    }
}
