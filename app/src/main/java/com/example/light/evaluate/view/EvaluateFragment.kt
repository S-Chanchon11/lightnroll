package com.example.light.evaluate.view

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chaquo.python.Python
import com.example.light.R
import com.example.light.evaluate.model.EvaluateModel
import com.example.light.evaluate.viewmodel.EvaluateViewModel
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class EvaluateFragment : Fragment() {

    private lateinit var openBtn: Button
    private lateinit var predictBtn: Button
    private var mStorageRef: StorageReference? = null
    private lateinit var viewModel: EvaluateViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_evaluate, container, false)
        // Inflate the layout for this fragment
        openBtn = view.findViewById(R.id.openBtn)
        predictBtn = view.findViewById(R.id.predict)
        mStorageRef = FirebaseStorage.getInstance().reference
        viewModel = ViewModelProvider(this)[EvaluateViewModel::class.java]

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        openBtn.setOnClickListener {
        }

        predictBtn.setOnClickListener {
        }
    }
    private fun submitAudio() {
        val py = Python.getInstance()
        val module = py.getModule("Tuner")
        val pcpFunc = module[ "extract" ]

        val externalStorageDirectory = Environment.getExternalStorageDirectory()
        var filename = "/Android/data/com.example.light/files"
        val file = File(externalStorageDirectory, filename)
        file.walk().forEach {
//            '/data/data/com.example.light/files/chaquopy/AssetFinder/app/Recording_0.wav'
            if (it.extension == "wav") {
                it.copyTo(File("/data/data/com.example.light/files/chaquopy/AssetFinder/app/" + it.name), true)
//                        uploadFile(it)`
//                        Log.d("From python",it.name).
// save audio file
// copy to asset path
// read by python
                val pcp = pcpFunc?.call(it.name.toString())
//                Log.d("From python", pcp.toString())
                val evaluateModel = EvaluateModel(
                    "123",
                    listOf(pcp!!.toDouble()),
                    22050
                )
                viewModel.sendData(evaluateModel)?.observe(
                    viewLifecycleOwner,
                    Observer { it ->
                        if (it != null) {
                            Log.d("observe", it.prediction)
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
    private fun observeData() {
        try {
            viewModel.getData()?.observe(
                viewLifecycleOwner,
                Observer { it ->
                    if (it != null) {
                        Log.d("observe", it.pcp.toString())
                    } else {
                        Log.d("observe", "is null")
                    }
                }
            )
        } catch (e: Exception) {
            print(e)
        }
    }
}