package com.example.light

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    lateinit var toFrag : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
//        ft.replace(R.id.fooFragment, test())
//        ft.commit()
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = test()
        toFrag = findViewById(R.id.button)

        toFrag.setOnClickListener {
            val mBundle = Bundle()
            mFragment.arguments = mBundle
            mFragmentTransaction.add(R.id.frameLayout, mFragment).commit()
            toFrag.visibility = View.GONE
        }

        // Declaring and initializing the EditText and Button from the layout

        // Declaring fragment manager from making data
        // transactions using the custom fragment


        // On button click, a bundle is initialized and the
        // text from the EditText is passed in the custom
        // fragment using this bundle

    }

}









