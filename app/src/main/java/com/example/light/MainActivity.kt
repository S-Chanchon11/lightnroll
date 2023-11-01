package com.example.light

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.light.practice.PracticeFragment
import com.example.light.record.RecordFragment
import com.example.light.record.SettingFragment
import com.example.light.upload.UploadFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val practiceFragment = PracticeFragment()
        val settingFragment = SettingFragment()
        val recordFragment = RecordFragment()
        val uploadFragment = UploadFragment()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.practice -> replaceFragment(practiceFragment)
                R.id.record -> replaceFragment(recordFragment)
                R.id.setting -> replaceFragment(settingFragment)
                R.id.upload -> replaceFragment(uploadFragment)
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
