package com.example.light.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.light.R
import com.example.light.UserManager
import com.example.light.evaluate.model.EvaluateResultModel
import com.example.light.evaluate.view.HistoryAdapter
import com.example.light.evaluate.viewmodel.EvaluateViewModel
import com.example.light.login.viewmodel.LoginViewModel
import com.example.light.profile.ProfileViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    val TAG = "HOME_FRAGMENT"
    private lateinit var viewModel: LoginViewModel
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var rootView: View
    private lateinit var adapter:HistoryAdapter
    private lateinit var hisList: List<EvaluateResultModel>
    private lateinit var recyclerView: RecyclerView
    private lateinit var evaluateViewModel: EvaluateViewModel
    private lateinit var startLesson : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uid = UserManager.getUid()
        Log.d(TAG, uid.toString())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_home_0, container, false)
        hisList = ArrayList()
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        viewModel.profiledata.observe(
            viewLifecycleOwner,
            Observer { user ->
//                Log.d(TAG,user.toString())

                val newLayoutId = when (user.user_level) {
                    0 -> return@Observer
                    2 -> R.layout.fragment_home_2
                    else -> { return@Observer }
                }
                replaceLayout(newLayoutId, container)
            }
        )

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var actionbar = (requireActivity() as AppCompatActivity).supportActionBar
        actionbar?.hide()
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        if(view.id == R.layout.fragment_home_2){
            startLesson = view.findViewById(R.id.startLessonButton)!!
            recyclerView = rootView.findViewById(R.id.recent_recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(view.context)

            adapter = HistoryAdapter(hisList)
            recyclerView.adapter = adapter
            evaluateViewModel.data.observe(
                viewLifecycleOwner,
                Observer { user ->
                    adapter.updateData(user)
                }
            )
            startLesson.setOnClickListener {

            }
        }
        navBar.visibility = View.VISIBLE
    }
    private fun replaceLayout(layoutId: Int, container: ViewGroup?) {
        val inflater = LayoutInflater.from(context)
        val newView = inflater.inflate(layoutId, container, false)

        // Remove the old view and add the new view
        container?.removeView(rootView)
        container?.addView(newView)
        rootView = newView
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Home", "OnDestroy")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Home", "OnPause")
    }
}
