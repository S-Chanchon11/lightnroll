package com.example.light

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity


class test : Fragment() {
    private lateinit var myTextView: TextView

    // Override function when the view is being created
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        // Inflates the custom fragment layout
        val view: View = inflater.inflate(R.layout.fragment_test, container, false)

        // Finds the TextView in the custom fragment
        myTextView = view.findViewById<View>(R.id.fragmentTextView) as TextView


        return view
    }
}