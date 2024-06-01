package com.example.light.expandableRecyclerview.repository

import com.example.light.expandableList.ui.PracticeFragment
import com.example.light.expandableRecyclerview.model.DestinationFragment
import com.example.light.expandableRecyclerview.model.LessonModel
import com.example.light.expandableRecyclerview.ui.RecyclerViewFragment

class LessonRepository() {

    fun getLessonInfo(): List<LessonModel> {
        val lst: List<LessonModel>
        lst = ArrayList()

        lst.add(LessonModel("Basic", "sample"))
        lst.add(LessonModel("Intermediate", "sample"))
        lst.add(LessonModel("Advance", "sample"))
        lst.add(LessonModel("Chord Library", "sample"))

        return lst
    }

    fun getBasicLessonInfo(): List<LessonModel> {
        val lst: List<LessonModel>
        lst = ArrayList()

        lst.add(LessonModel("Title_A", "Desc_A"))
        lst.add(LessonModel("Title_B", "Desc_B"))
        lst.add(LessonModel("Title_C", "Desc_C"))
        lst.add(LessonModel("Title_D", "Desc_D"))
        lst.add(LessonModel("Title_E", "Desc_E"))

        return lst
    }
    fun getSubLessonInfo(level: Int): List<DestinationFragment> {
        val lst: List<DestinationFragment>
        lst = ArrayList()

        when (level) {
            0 -> {
                lst.add(DestinationFragment(PracticeFragment(), "BASIC_Guitar Anatomy.pdf"))
                lst.add(DestinationFragment(PracticeFragment(), "BASIC_Chord Reading.pdf"))
                lst.add(DestinationFragment(PracticeFragment(), "BASIC_Strumming.pdf"))
                lst.add(DestinationFragment(RecyclerViewFragment(), "Major"))
            }
            1 -> {
                lst.add(DestinationFragment(PracticeFragment(), "basic_1.pdf"))
                lst.add(DestinationFragment(PracticeFragment(), "basic_1.pdf"))
                lst.add(DestinationFragment(PracticeFragment(), "basic_1.pdf"))
                lst.add(DestinationFragment(PracticeFragment(), "basic_1.pdf"))
                lst.add(DestinationFragment(RecyclerViewFragment(), "Minor"))
            }
            2 -> {
                lst.add(DestinationFragment(RecyclerViewFragment(), "Maj7"))
                lst.add(DestinationFragment(PracticeFragment(), "basic_1.pdf"))
                lst.add(DestinationFragment(PracticeFragment(), "basic_1.pdf"))
            }
        }

        return lst
    }

    fun getIntermediateLessonInfo(): List<LessonModel> {
        val lst: List<LessonModel>
        lst = ArrayList()

        lst.add(LessonModel("Title_A", "Desc_A"))
        lst.add(LessonModel("Title_B", "Desc_B"))
        lst.add(LessonModel("Title_C", "Desc_C"))
        lst.add(LessonModel("Title_D", "Desc_D"))
        lst.add(LessonModel("Title_E", "Desc_E"))
        lst.add(LessonModel("Title_F", "Desc_F"))

        return lst
    }
    fun getAdvancedLessonInfo(): List<LessonModel> {
        val lst: List<LessonModel>
        lst = ArrayList()

        lst.add(LessonModel("Title_A", "Desc_A"))
        lst.add(LessonModel("Title_B", "Desc_B"))
        lst.add(LessonModel("Title_C", "Desc_C"))
        lst.add(LessonModel("Title_D", "Desc_D"))

        return lst
    }

//    fun replaceFragment(fragment: Fragment, tag: String) {
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.frame_layout, fragment, tag).addToBackStack(tag)
//        fragmentTransaction.commit()
//    }
}
