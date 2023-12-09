package com.example.light.ui

import android.Manifest
import android.Manifest.permission.RECORD_AUDIO
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.light.R
import java.io.IOException
import java.time.LocalDateTime.now

class RecordFragment : Fragment(){

    private lateinit var startbtn: Button
    private lateinit var stopbtn : Button
    private lateinit var playbtn : Button
    private lateinit var stopplay:Button
    private var mRecorder: MediaRecorder? = null
    private var mPlayer: MediaPlayer? = null
    private val LOG_TAG = "AudioRecording"
    private var mFileName: String? = null
    var REQUEST_AUDIO_PERMISSION_CODE = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_record, container, false)


        return view
    }
    private val PermissionResultReceiver = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (it) {
            startbtn.isEnabled = true

        } else {
            startbtn.isEnabled = false
        }
    }
    private fun requestPermission1() {
        PermissionResultReceiver.launch(RECORD_AUDIO)
    }
    private fun requestPermission2() {
        PermissionResultReceiver.launch(WRITE_EXTERNAL_STORAGE)
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }

}
