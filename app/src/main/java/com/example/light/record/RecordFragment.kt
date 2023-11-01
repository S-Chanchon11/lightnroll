package com.example.light.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewTreeLifecycleOwner.set
import com.example.light.R

class RecordFragment : Fragment(){
    private lateinit var recordTitle : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_record, container, false)
        recordTitle = view?.findViewById(R.id.recordTitle)!!


        return view
    }
}
