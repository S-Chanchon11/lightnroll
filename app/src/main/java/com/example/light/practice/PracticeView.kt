package com.example.light.practice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.light.R


class PracticeView : Fragment() {
    private lateinit var pracModelBinding: ViewDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        pracModelBinding =
            DataBindingUtil.inflate(inflater, R.layout.practice_view_fragment, container, false)
        return pracModelBinding.root

    }
}