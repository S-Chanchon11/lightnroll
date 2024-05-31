package com.example.light

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.light.evaluate.EvaluateFragment
import com.example.light.expandableList.ui.PracticeFragment
import com.example.light.expandableRecyclerview.ui.LessonFragment
import com.example.light.expandableRecyclerview.ui.RecyclerViewFragment
import com.example.light.record.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.unity3d.player.UnityPlayerActivity

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolBar: Toolbar

//    var listener: OnCardClickListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val actionBar = supportActionBar
//        actionBar!!.title = "  GfG | Action Bar"
//        actionBar!!.subtitle = "   Design a custom Action Bar"
//        actionBar!!.setIcon(R.drawable.birthday_cake_stroke_rounded)
//        actionBar!!.setDisplayUseLogoEnabled(true)
//        actionBar!!.setDisplayShowHomeEnabled(true)

        val practiceFragment = PracticeFragment()
        val settingFragment = SettingFragment()
        val recyclerFragment = RecyclerViewFragment()
//        val tunerFragment = TunerFragment()
        val lessonFragment = LessonFragment()
        val evaluateFragment = EvaluateFragment()
        toolBar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)
        val actionBar = supportActionBar
        actionBar!!.title = "Light n roll"

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    actionBar!!.title = "Light n roll"
//                    actionBar.setDisplayShowHomeEnabled(false)
//                    actionBar.setDisplayHomeAsUpEnabled(false)
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
                R.id.lesson -> {
//                    actionBar.setDisplayShowHomeEnabled(true)
//                    actionBar.setDisplayHomeAsUpEnabled(true) // set back button
                    actionBar!!.title = "Lesson"
                    replaceFragment(lessonFragment, "lesson")
                }
                R.id.evaluate -> {
                    actionBar!!.title = "Evaluate"
                    replaceFragment(evaluateFragment, "")
                }
                R.id.tuning -> gotoTuner()
                R.id.profile -> replaceFragment(practiceFragment,"")
            }
            true
        }
        Log.d(localClassName, "onCreate")
//        listener.onCardClick(SlideshowFragment())


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
        fragmentTransaction.replace(R.id.frame_layout, fragment, tag).addToBackStack( tag )
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
}
