package com.example.light.lesson

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.light.R
import com.example.light.chord.ui.ChordFragment

class LessonFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var lessonList: List<LessonModel>
    private lateinit var subLessonList: List<DestinationFragment>
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
        val recyclerFragment = ChordFragment()
        var actionbar = (requireActivity() as AppCompatActivity).supportActionBar
        actionbar?.show()
        var choice: Int
        adapter.setOnClickListener(object :
                LessonAdapter.OnClickListener {
                override fun onClick(position: Int, model: LessonModel) {
                    when (position) {
                        0 -> {
                            actionbar?.title = "\tBasic Level"
                            lessonList = viewModel.loadLessonData(position)
                        }
                        1 -> {
                            actionbar?.title = "\tIntermediate Level"
                            lessonList = viewModel.loadLessonData(position)
                        }
                        2 -> {
                            actionbar?.title = "\tAdvanced Level"
                            lessonList = viewModel.loadLessonData(position)
                        }
                        3 -> {
                            replaceFragment(recyclerFragment)
                            setFragmentResult(
                                "requestKey",
                                bundleOf("bundleKey" to position.toString())
                            )
                        }
                    }
                    choice = position
                    adapter = LessonAdapter(lessonList, view.context)
                    recyclerView.adapter = adapter
                    subLessonList = viewModel.loadSubLessonData(choice)
                    adapter.setOnClickListener(object :
                            LessonAdapter.OnClickListener {
                            override fun onClick(position: Int, model: LessonModel) {
                                replaceFragment(subLessonList[position].fragment)
                                val result = Bundle().apply {
                                    putString("bundleKey", subLessonList[position].filename)
                                }
                                setFragmentResult("detailKey", result)
                            }
                        })
                }
            })
    }
    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
    fun refreshFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.detach(fragment)
        fragmentTransaction.attach(fragment)
        fragmentTransaction.commit()
    }

    override fun onStart() {
        super.onStart()
        Log.d("LessonFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LessonFragment", "onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LessonFragment", "onDestroy")
    }
}
