package com.example.light.evaluate.view

import android.content.ContentValues.TAG
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chaquo.python.Python
import com.example.light.R
import com.example.light.UserManager
import com.example.light.evaluate.model.EvaluateModel
import com.example.light.evaluate.model.EvaluateResultModel
import com.example.light.evaluate.model.EvaluateSongModel
import com.example.light.evaluate.viewmodel.EvaluateViewModel
import com.example.light.utilities.SpaceItemDecoration
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class EvaluateFragment : Fragment() {
    val TAG = "EvaluateFragment"
    private var mStorageRef: StorageReference? = null
    private lateinit var viewModel: EvaluateViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var hisRecyclerView: RecyclerView
    private lateinit var adapter: EvaluateAdapter
    private lateinit var hisAdapter: HistoryAdapter
    private lateinit var songList: List<EvaluateSongModel>
    private lateinit var hisList: List<EvaluateResultModel>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_evaluate, container, false)
        recyclerView = view.findViewById(R.id.recyclerview)
        hisRecyclerView = view.findViewById(R.id.second_recyclerview)
        mStorageRef = FirebaseStorage.getInstance().reference
        songList = ArrayList()
        hisList = ArrayList()
        viewModel = ViewModelProvider(this)[EvaluateViewModel::class.java]

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        songList = viewModel.getSongChoice()
        adapter = EvaluateAdapter(songList, view.context)

        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(SpaceItemDecoration(16))
//        observeResult()
//        hisRecyclerView.adapter = hisAdapter
//        viewModel.data
        viewModel.data.observe(
            viewLifecycleOwner,
            Observer { user ->
                Log.d(TAG,user.toString())
//                val newLayoutId = when(user[0].rid.toString()) {
//                    0 -> return@Observer
//                    2 -> R.layout.fragment_home_2
//                    else -> {return@Observer}
//                }
//                replaceLayout(newLayoutId, container)
            }
        )
//        hisList = viewModel.fetchDataFromApi().value!!
//        Log.d(TAG,hisList.toString())
    }
//    private fun observeResult(){
//        viewModel.data.observe(
//            viewLifecycleOwner,
//            Observer {data ->
//                Log.d("EvaluateFragment",data[0].rid.toString())
//            }
//        )
//
//    }
    private fun submitAudio() {
        val py = Python.getInstance()
        val module = py.getModule("Tuner")
        val pcpFunc = module[ "extract" ]

        val externalStorageDirectory = Environment.getExternalStorageDirectory()
        var filename = "/Android/data/com.example.light/files"
        val file = File(externalStorageDirectory, filename)
        file.walk().forEach {

            if (it.extension == "wav") {

                it.copyTo(File("/data/data/com.example.light/files/chaquopy/AssetFinder/app/" + it.name), true)

                val pcp = pcpFunc?.call(it.name.toString())

                val evaluateModel = EvaluateModel(
                    listOf(pcp!!.toDouble())
                )
                viewModel.sendData(evaluateModel)?.observe(
                    viewLifecycleOwner,
                    Observer { it ->
                        if (it != null) {
                            Log.d("observe", it.prediction_result.toString())
                        } else {
                            Log.d("observe", "is null")
                        }
                    }
                )
            }
        }
    }
    private fun uploadFile(file: File) {
        val fileUri = Uri.fromFile(file)
        val fileRef = mStorageRef!!.child("audio/" + file.name)
        Log.d("Evaluate", file.name.toString())
        fileRef.putFile(fileUri)
            .addOnSuccessListener {
                // Handle successful uploads on complete
                // Get the download URL
                fileRef.downloadUrl.addOnSuccessListener { uri ->
                    val downloadUrl = uri.toString()
                    // Do something with the download URL
//                    Log.d("Evaluate", downloadUrl.toString())
                }
            }
            .addOnFailureListener {
                // Handle unsuccessful uploads
                Log.d("Evaluate", "why????")
            }
    }
}
