package com.example.light.chord.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.light.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class ChordActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolBar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolBar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)

        val actionBar = supportActionBar
        actionBar!!.title = "Chord Library"
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.visibility = View.GONE
    }
}
