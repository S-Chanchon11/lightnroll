package com.example.light

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.light.evaluate.EvaluateFragment
import com.example.light.expandableRecyclerview.ui.LessonFragment
import com.example.light.home.HomeFragment
import com.example.light.login.viewmodel.LoginViewModel
import com.example.light.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.unity3d.player.UnityPlayerActivity

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolBar: Toolbar
    private lateinit var titleTxt: TextView
    private lateinit var viewModel: LoginViewModel

    //    var listener: OnCardClickListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

//        val actionBar = supportActionBar
//        actionBar!!.title = "  GfG | Action Bar"
//        actionBar!!.subtitle = "   Design a custom Action Bar"
//        actionBar!!.setIcon(R.drawable.birthday_cake_stroke_rounded)
//        actionBar!!.setDisplayUseLogoEnabled(true)
//        actionBar!!.setDisplayShowHomeEnabled(true)
        titleTxt = findViewById(R.id.toolbar_title)
        val lessonFragment = LessonFragment()
        val evaluateFragment = EvaluateFragment()
        val homeFragment = HomeFragment()
        val profileFragment = ProfileFragment()

        toolBar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)
        val actionBar = supportActionBar
        actionBar!!.title = ""

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        actionBar!!.hide()
        replaceFragment(homeFragment, "home")

        titleTxt.visibility = View.GONE
        bottomNavigationView.setOnItemSelectedListener {
            titleTxt.visibility = View.VISIBLE
            when (it.itemId) {
                R.id.home -> {
                    titleTxt.visibility = View.GONE
//                    actionBar.hide()
                    replaceFragment(homeFragment, "home")
//                    actionBar.setDisplayShowHomeEnabled(false)
//                    actionBar.setDisplayHomeAsUpEnabled(false)
                }
                R.id.lesson -> {
                    actionBar.show()
                    titleTxt.text = "Lesson"
//                    actionBar.setDisplayShowHomeEnabled(true)
//                    actionBar.setDisplayHomeAsUpEnabled(true) // set back button
//                    actionBar!!.title = "Lesson"
                    replaceFragment(lessonFragment, "lesson")
                }
                R.id.evaluate -> {
                    titleTxt.text = "Evaluate"
                    replaceFragment(evaluateFragment, "")
                }
                R.id.tuning -> gotoTuner()
                R.id.profile -> {
                    titleTxt.visibility = View.GONE
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

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
// //            R.id.home -> this.finish()
//        }
//        return super.onOptionsItemSelected(item)
//    }
    fun replaceFragment(fragment: Fragment, tag: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment, tag).addToBackStack(tag)
        fragmentTransaction.commit()
    }
    override fun onStart() {
        super.onStart()
        Log.d(localClassName, "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(localClassName, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(localClassName, "onDestroy")
    }
    private fun gotoTuner() {
        val intent = Intent(this, UnityPlayerActivity::class.java)
        startActivity(intent)
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
