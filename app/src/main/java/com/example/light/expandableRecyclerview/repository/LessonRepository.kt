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
}
