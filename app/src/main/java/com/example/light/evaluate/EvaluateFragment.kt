package com.example.light.evaluate


import android.media.MediaDataSource
import android.media.MediaExtractor
import android.media.MediaFormat
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.chaquo.python.Python
import com.example.light.R
import java.io.FileDescriptor
import java.io.IOException


class EvaluateFragment : Fragment() {

    private lateinit var openBtn : Button
    private lateinit var predictBtn : Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_evaluate, container, false)
        // Inflate the layout for this fragment
        openBtn = view.findViewById(R.id.openBtn)
        predictBtn = view.findViewById(R.id.predict)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val py = Python.getInstance()
        val module = py.getModule( "Tuner" )
        val sumFunc = module[ "extract" ]
        val sum = sumFunc?.call("out_0.wav")
//        val sum = sumFunc?.call( "python/Tuner.py" )
        Log.d("EvaluateFragment", sum.toString())
        openBtn.setOnClickListener {
//            val mediaPlayer = MediaPlayer.create(context, R.raw.out_0)
//            mediaPlayer.start()
        }



//        predictBtn.setOnClickListener {
//            val model = MyModel2.newInstance(view.context)
//
//// Creates inputs for reference.
//            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 12), DataType.FLOAT32)
//            inputFeature0.loadBuffer(byteBuffer)
//
//// Runs model inference and gets result.
//            val outputs = model.process(inputFeature0)
//            val outputFeature0 = outputs.outputFeature0AsTensorBuffer
//
//// Releases model resources if no longer used.
//            model.close()
//        }

    }



}