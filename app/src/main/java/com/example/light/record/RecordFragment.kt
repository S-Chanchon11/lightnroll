package com.example.light.record

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

        startbtn = view.findViewById(R.id.recordBtn)
        stopbtn = view.findViewById(R.id.stoprecBtn)
        playbtn = view.findViewById(R.id.playBtn)
        stopplay = view.findViewById(R.id.stopPlayBtn)
        stopbtn.setEnabled(false)
        playbtn.setEnabled(false)
        stopplay.setEnabled(false)
        mFileName = Environment.getExternalStorageDirectory().absolutePath
        mFileName += "/AudioRecording.3gp"


        requestPermission1()
        requestPermission2()

        startbtn!!.setOnClickListener {
                stopbtn.setEnabled(true)
                startbtn!!.isEnabled = false
                playbtn.setEnabled(false)
                stopplay.setEnabled(false)
                mRecorder = MediaRecorder()
                mRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
                mRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                mRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                mRecorder!!.setOutputFile(mFileName)
                Log.e(LOG_TAG, mRecorder.toString())
                try {
                    mRecorder!!.prepare()
                } catch (e: IOException) {
                    Log.e(LOG_TAG, "prepare() failed")
                }
                mRecorder!!.start()
                Toast.makeText(context, "Recording Started", Toast.LENGTH_LONG)
                    .show()

        }
        stopbtn.setOnClickListener(View.OnClickListener {
            //requestPermission2()
            stopbtn.setEnabled(false)
            startbtn!!.isEnabled = true
            playbtn.setEnabled(true)
            stopplay.setEnabled(true)
            mRecorder!!.stop()
            mRecorder!!.release()
            mRecorder = null
            Toast.makeText(context, "Recording Stopped", Toast.LENGTH_LONG).show()
        })
        playbtn.setOnClickListener(View.OnClickListener {
            stopbtn.setEnabled(false)
            startbtn!!.isEnabled = true
            playbtn.setEnabled(false)
            stopplay.setEnabled(true)
            mPlayer = MediaPlayer()
            try {
                mPlayer!!.setDataSource(mFileName)
                mPlayer!!.prepare()
                mPlayer!!.start()
                Toast.makeText(
                    context,
                    "Recording Started Playing",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }
        })
        stopplay.setOnClickListener(View.OnClickListener {
            mPlayer!!.release()
            mPlayer = null
            stopbtn.setEnabled(false)
            startbtn!!.isEnabled = true
            playbtn.setEnabled(true)
            stopplay.setEnabled(false)
            Toast.makeText(context, "Playing Audio Stopped", Toast.LENGTH_SHORT)
                .show()
        })
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
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        when (requestCode) {
//            REQUEST_AUDIO_PERMISSION_CODE -> if (grantResults.size > 0) {
//                val permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED
//                val permissionToStore = grantResults[1] == PackageManager.PERMISSION_GRANTED
//                if (permissionToRecord && permissionToStore) {
//                    Toast.makeText(context, "Permission Granted", Toast.LENGTH_LONG)
//                        .show()
//                } else {
//                    Toast.makeText(context, "Permission Denied", Toast.LENGTH_LONG)
//                        .show()
//                }
//            }
//        }
//    }

//    fun CheckPermissions(): Boolean {
//        val permissionLauncher = registerForActivityResult(
//            ActivityResultContracts.RequestPermission()
//        ) { isGranted ->
//            if (isGranted) {
//                REQUEST_AUDIO_PERMISSION_CODE = 1
//            }
//            else {
//                REQUEST_AUDIO_PERMISSION_CODE = 0
//            }
//
//        }
//        permissionLauncher.launch(RECORD_AUDIO)
//        permissionLauncher.launch(WRITE_EXTERNAL_STORAGE)
//
//    }





//    private lateinit var recordBtn : Button
//    private var recorder: MediaRecorder? = null
//    private var recording=false
//    private var isRecordable=true
//    private val requestPermission =
//        registerForActivityResult(ActivityResultContracts.RequestPermission())
//        { isGranted: Boolean ->
//            if (isGranted) {
//
//            } else {
//                isRecordable=false
//                Log.d("ddddd","permission")
//            }
//        }



//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        recordBtn.setOnClickListener{
//            if(!recording) {
//                Log.d("recording", "start")
//                record()
//            }
//            else
//                stopRecord()
//                Log.d("recording", "stop")
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
//    fun record(){
//        Log.d("ddddd", now().toString())
//        if(!isRecordable)
//            return
//
//        var mFileName:String = Environment.getExternalStorageDirectory().absolutePath+"/Recordings/"+ now().toString()+"test.3gp"
//        println(Environment.getExternalStorageState())
//        recorder = MediaRecorder().apply {
//            setAudioSource( MediaRecorder.AudioSource.DEFAULT)
//            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
//            setOutputFile(mFileName)
//            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
//            try {
//                prepare()
//            } catch (e: IOException) {
//                println(e)
//            }
//            start()
//        }
//        recording=true
//    }
//    fun stopRecord(){
//        recorder?.apply {
//            stop()
//            release()
//        }
//        recorder = null
//        recording=false
//    }
}
