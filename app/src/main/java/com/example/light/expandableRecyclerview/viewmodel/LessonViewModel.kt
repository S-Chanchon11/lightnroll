package com.example.light.expandableRecyclerview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.light.expandableRecyclerview.model.LessonModel
import com.example.light.expandableRecyclerview.repository.LessonRepository

class LessonViewModel : ViewModel() {
    private val lessonRepository = LessonRepository()
    private val data = MutableLiveData<List<LessonModel>>()
    val items: LiveData<List<LessonModel>> get() = data
    fun loadLessonData(): List<LessonModel> {
        data.value = lessonRepository.getLessonInfo()

        return data.value!!
    }

//    fun updateItem(clickedItem: LessonModel) {
//        data.value = data.value?.map {
//            if (it.title == clickedItem.title) {
//                LessonModel(it.title, "${it.description} (Updated)")
//            } else {
//                it
//            }
//        }
//    }
    fun loadBasicLessonData(): List<LessonModel> {
        data.value = lessonRepository.getBasicLessonInfo()

        return data.value!!
    }
}
