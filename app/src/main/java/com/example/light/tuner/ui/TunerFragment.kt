package com.example.light.tuner.ui

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
//import be.tarsos.dsp.AudioProcessor
//import be.tarsos.dsp.io.android.AudioDispatcherFactory
//import be.tarsos.dsp.pitch.PitchDetectionHandler
//import be.tarsos.dsp.pitch.PitchProcessor
//import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm
import com.example.light.R

class TunerFragment : Fragment() {

//      Pitch of each string
//    82.41 Hz Low E (6th string)
//    110.00 Hz A (5th string)
//    146.83 Hz D (4th string)
//    196.00 Hz G (3rd string)
//    246.94 Hz B (2nd string)
//    329.63 Hz High E (1st string)

    private lateinit var pitchText: TextView
    private lateinit var noteText: TextView
    private lateinit var stopButton: Button
    private lateinit var startButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_tuner, container, false)

        pitchText = view.findViewById(R.id.pitch)
        noteText = view.findViewById(R.id.note)
        stopButton = view.findViewById(R.id.stopBtn)
        startButton = view.findViewById(R.id.stopBtn)
        Log.d("PY", "onCreateView")

        return view
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val permissionLauncher = registerForActivityResult(
//            ActivityResultContracts.RequestPermission()
//        ) { isGranted ->
//            if (isGranted) {
//                val dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050, 1024, 0)
//                val pdh = PitchDetectionHandler { res, e ->
//                    val pitchInHz = res.pitch
//                    requireActivity().runOnUiThread { processPitch(pitchInHz) }
//                }
//                val pitchProcessor: AudioProcessor =
//                    PitchProcessor(PitchEstimationAlgorithm.FFT_YIN, 22050f, 1024, pdh)
//                dispatcher.addAudioProcessor(pitchProcessor)
//
//                val audioThread = Thread(dispatcher, "Audio Thread")
//                audioThread.start()
//                noteText.setText("Recording")
//                startButton.setOnClickListener {
//                    Log.d("thread", "click start")
//                }
//
//                stopButton.setOnClickListener {
//                    noteText.setText("Stop recording")
//                    Thread.sleep(1000)
//                    Log.d("thread", "click stop")
//                    audioThread.interrupt()
//                    dispatcher.removeAudioProcessor(pitchProcessor)
//                    dispatcher.stop()
//                }
//            } else {
//                // Do otherwise
//            }
//        }
//        permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
//    }
//
//    fun processPitch(pitchInHz: Float) {
//        pitchText.setText("" + pitchInHz)
//
//        if (pitchInHz >= 110 && pitchInHz < 123.47) {
//            // A
//            noteText.setText("A")
//        } else if (pitchInHz >= 123.47 && pitchInHz < 130.81) {
//            // B
//            noteText.setText("B")
//        } else if (pitchInHz >= 130.81 && pitchInHz < 146.83) {
//            // C
//            noteText.setText("C")
//        } else if (pitchInHz >= 146.83 && pitchInHz < 164.81) {
//            // D
//            noteText.setText("D")
//        } else if (pitchInHz >= 164.81 && pitchInHz <= 174.61) {
//            // E
//            noteText.setText("E")
//        } else if (pitchInHz >= 174.61 && pitchInHz < 185) {
//            // F
//            noteText.setText("F")
//        } else if (pitchInHz >= 185 && pitchInHz < 196) {
//            // G
//            noteText.setText("G")
//        }
//    }
}
