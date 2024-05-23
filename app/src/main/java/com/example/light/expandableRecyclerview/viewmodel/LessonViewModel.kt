package com.example.light.expandableRecyclerview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.light.expandableRecyclerview.model.LessonModel
import com.example.light.expandableRecyclerview.repository.LessonRepository

class LessonViewModel : ViewModel() {
    private val lessonRepository = LessonRepository()
    private val data = MutableLiveData<List<LessonModel>>()
    fun loadLessonData(): List<LessonModel> {
        data.value = lessonRepository.getLessonInfo()
//        Log.d("VM", data.value.toString())
        return data.value!!
    }
}
