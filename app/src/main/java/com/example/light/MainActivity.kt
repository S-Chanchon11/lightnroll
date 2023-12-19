package com.example.light

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.light.practice.ui.PracticeFragment
import com.example.light.record.RecordFragment
import com.example.light.record.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val practiceFragment = PracticeFragment()
        val settingFragment = SettingFragment()
        val recordFragment = RecordFragment()
        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        Log.d("Activity", "Main")
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.practice -> replaceFragment(practiceFragment)
                R.id.record -> replaceFragment(recordFragment)
                R.id.setting -> replaceFragment(settingFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}
