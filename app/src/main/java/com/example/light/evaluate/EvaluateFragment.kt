package com.example.light.evaluate

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.chaquo.python.Python
import com.example.light.R
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class EvaluateFragment : Fragment() {

    private lateinit var openBtn: Button
    private lateinit var predictBtn: Button
    private var mStorageRef: StorageReference? = null
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
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val py = Python.getInstance()
        val module = py.getModule("Tuner")
        val sumFunc = module[ "extract" ]

        val externalStorageDirectory = Environment.getExternalStorageDirectory()
        var filename = "/Android/data/com.example.light/files"
//        var filename = "/chaquopy/AssetFinder/app"

//        val file = File(requireContext().filesDir.path, filename)
        val file = File(externalStorageDirectory, filename)

        file.walk().forEach {
            Log.d("Egdfgasdfgsdfgsdfg", it.toString())

//            '/data/data/com.example.light/files/chaquopy/AssetFinder/app/Recording_0.wav'
            if (it.extension == "wav") {
//                it.renameTo(File("/data/data/com.example.light/files/chaquopy/AssetFinder/app/"+it.name))
                it.copyTo(File("/data/data/com.example.light/files/chaquopy/AssetFinder/app/" + it.name), true)
//                Log.d("isWAV", it.name)
//                        uploadFile(it)
//                        Log.d("From python",it.name).
// save audio file
// copy to asset path
// read by python
                val sum = sumFunc?.call(it.name.toString())
                Log.d("From python", sum.toString())
//                        Log.d("Evaluate", it.toString())
            }
        }
        predictBtn.setOnClickListener {
            if (file.exists()) {
            } else {
                Toast.makeText(context, "Unable to upload", Toast.LENGTH_SHORT).show()
            }

// /storage/emulated/0/Android/data/com.example.light/files/Recording_0.wav
        }

//        val chords = listOf(
//            Chord(0.00, 1.50, "G"),
//            Chord(1.50, 3.00, "D"),
//            Chord(3.00, 4.50, "Em")
//        )
//        val song = SongModel(1, "Song A", 120, chords)
//
//        // Convert the Song object to JSON string
//        val gson = Gson()
//        val jsonString = gson.toJson(song)

        // Send the JSON string to Unity
//        UnityPlayer.UnitySendMessage("JsonReceiver", "ReceiveMessage", "test")

//        Log.d("Evaluate", "send message")
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
