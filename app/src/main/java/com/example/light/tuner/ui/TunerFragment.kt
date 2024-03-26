package com.example.light.tuner.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import be.tarsos.dsp.AudioDispatcher
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory
import com.example.light.R

class TunerFragment : Fragment() {

//      Pitch of each string
//    82.41 Hz Low E (6th string)
//    110.00 Hz A (5th string)
//    146.83 Hz D (4th string)
//    196.00 Hz G (3rd string)
//    246.94 Hz B (2nd string)
//    329.63 Hz High E (1st string)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_tuner, container, false)
        Log.d("PY", "TunerFragment")
        val dispatcher: AudioDispatcher =
            AudioDispatcherFactory.fromDefaultMicrophone(22050, 1024, 0)
//        onPitchListener()
        // Inflate the layout for this fragment
        return view
    }
}
