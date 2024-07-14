package com.example.light.evaluate.view

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
import com.example.light.evaluate.model.EvaluateUpdateModel
import com.example.light.evaluate.viewmodel.EvaluateViewModel
import com.example.light.utilities.SpaceItemDecoration
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
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
//        hisRecyclerView = view.findViewById(R.id.second_recyclerview)
        mStorageRef = FirebaseStorage.getInstance().reference
        songList = ArrayList()
        hisList = ArrayList()
        viewModel = ViewModelProvider(this)[EvaluateViewModel::class.java]

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
//        hisRecyclerView.layoutManager = LinearLayoutManager(view.context)
        songList = viewModel.getSongChoice()

        adapter = EvaluateAdapter(songList, view.context)
//        hisAdapter = HistoryAdapter(hisList)

        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(SpaceItemDecoration(16))
//        hisRecyclerView.adapter = hisAdapter
//
//        viewModel.data.observe(
//            viewLifecycleOwner,
//            Observer { user ->
//                hisAdapter.updateData(user)
//            }
//        )
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, UserManager.getRid().toString())
        if (UserManager.getRid().toString() != "") {
            viewModel.getResultByRID(UserManager.getRid().toString()).observe(
                viewLifecycleOwner,
                Observer { user ->
                    submitAudio(user.song_name)
                    Log.d(TAG, "getResultByRID")
                }
            )
        }
    }
    private fun submitAudio(songname: String) {
        val py = Python.getInstance()
        val module = py.getModule("Tuner")
        val pcpFunc = module[ "extract" ]

        val externalStorageDirectory = Environment.getExternalStorageDirectory()
        var filename = "/Android/data/com.example.light/files"
        val file = File(externalStorageDirectory, filename)

        file.walk().forEach {
            if (it.extension == "wav") {
                it.copyTo(File("/data/data/com.example.light/files/chaquopy/AssetFinder/app/" + it.name), true)
                Log.d(TAG,it.name)
                uploadAudioFile(UserManager.getRid().toString(), it.path, it.name)

//                val pcp = pcpFunc?.call(it.name.toString())
//
//                Log.d(TAG, "formattedPCP" + pcp.toString())
//
//                val pyList: List<PyObject> = pcp!!.asList()
//                floatList = ArrayList()
//
//                for (pyObj in pyList) {
//                    (floatList as ArrayList<Double>).add(pyObj.toDouble())
//                }
//                fullList.add(floatList)
            }
            Log.d(TAG,"DONE")
        }
//        Log.d(TAG, fullList.toString())

        viewModel.sendData(
            EvaluateModel(
                UserManager.getRid().toString(),
                75
            )
        )?.observe(
            viewLifecycleOwner,
            Observer { it ->
                if (it != null) {
                    updateData(
                        EvaluateUpdateModel(
                            UserManager.getRid().toString(),
                            songname,
                            it.detected_tempo,
                            it.pred_result
                        )
                    )
                }
            }
        )
    }
    fun uploadAudioFile(rid: String, filePath: String, filename: String) {
        // Create a reference to the Firebase Storage location
        val storageRef = Firebase.storage.reference

        // File (local path) to upload
        val file = Uri.fromFile(File(filePath))

        // Create a reference to 'uid/audio/filename'
        val audioRef = storageRef.child("$rid/$filename")

        // Upload file to Firebase Storage
        val uploadTask = audioRef.putFile(file)

        // Register observers to listen for upload success or failure
        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            // Continue with the task to get the download URL
            audioRef.downloadUrl
        }.addOnCompleteListener(
            OnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    Log.d("GET URL", downloadUri.toString()) // Pass the download URL to the callback function
                } else {
                    // Handle failures
                    Log.d("GET URL", "not found")
                }
            }
        )
    }
    private fun updateData(data: EvaluateUpdateModel) {
        Log.d(TAG, data.toString())
        viewModel.updateResultByRID(data)?.observe(
            viewLifecycleOwner,
            Observer {
                Log.d(TAG, it.toString())
            }
        )
    }
}
