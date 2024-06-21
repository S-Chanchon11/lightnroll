package com.example.light

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.light.evaluate.view.EvaluateFragment
import com.example.light.home.HomeFragment
import com.example.light.lesson.LessonFragment
import com.example.light.login.viewmodel.LoginViewModel
import com.example.light.profile.ProfileFragment
import com.example.light.tuner.TunerFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolBar: Toolbar
    private lateinit var viewModel: LoginViewModel

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        val lessonFragment = LessonFragment()
        val evaluateFragment = EvaluateFragment()
        val homeFragment = HomeFragment()
        val profileFragment = ProfileFragment()
        val tunerFragment = TunerFragment()
        toolBar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)
        toolBar.setTitleMargin(550, 0, 0, 0)
        toolBar.visibility = View.GONE
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        replaceFragment(homeFragment, "home")

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(homeFragment, "home")
                }

                R.id.lesson -> {
                    toolBar.title = "Lesson"
                    toolBar.visibility = View.VISIBLE
                    replaceFragment(lessonFragment, "lesson")
                }

                R.id.evaluate -> {
                    toolBar.title = "Evaluate"
                    toolBar.visibility = View.VISIBLE
                    replaceFragment(evaluateFragment, "")
                }

                R.id.tuning -> replaceFragment(tunerFragment, "")
                R.id.profile -> {
                    toolBar.visibility = View.GONE
                    replaceFragment(profileFragment, "")
                }
            }
            true
        }
        Log.d(localClassName, "onCreate")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        inflater.inflate(R.menu.action_menu, menu)
        return true
    }

    fun replaceFragment(fragment: Fragment, tag: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
//        removeFragment()
        fragmentTransaction.replace(R.id.frame_layout, fragment, tag).addToBackStack(tag)
        fragmentTransaction.commit()
    }

    private fun removeFragment() {
        supportFragmentManager.fragments.let {
            if (it.isNotEmpty()) {
                supportFragmentManager.beginTransaction().apply {
                    for (fragment in it) {
                        remove(fragment)
                    }
                    commit()
                }
            }
        }
    }

}
