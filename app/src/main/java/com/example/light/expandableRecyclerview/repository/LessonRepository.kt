package com.example.light.expandableRecyclerview.repository

import com.example.light.expandableRecyclerview.model.LessonModel

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

        return lst
    }
}
