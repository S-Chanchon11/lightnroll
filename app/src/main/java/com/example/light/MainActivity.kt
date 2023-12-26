package com.example.light

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.light.expandableList.ui.PracticeFragment
import com.example.light.expandableRecyclerview.ui.RecyclerViewFragment
import com.example.light.record.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val practiceFragment = PracticeFragment()
        val settingFragment = SettingFragment()
//        val recordFragment = RecordFragment()
        val recyclerFragment = RecyclerViewFragment()
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.practice -> replaceFragment(practiceFragment)
                R.id.record -> replaceFragment(recyclerFragment)
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
