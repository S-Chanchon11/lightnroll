package com.example.light.expandableRecyclerview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.light.expandableRecyclerview.model.DestinationFragment
import com.example.light.expandableRecyclerview.model.LessonModel
import com.example.light.expandableRecyclerview.repository.LessonRepository

class LessonViewModel : ViewModel() {
    private val lessonRepository = LessonRepository()
    private val lessonData = MutableLiveData<List<LessonModel>>()
    private val subLessonData = MutableLiveData<List<DestinationFragment>>()

//    val items: LiveData<List<LessonModel>> get() = lessonData
    fun loadLessonData(): List<LessonModel> {
        lessonData.value = lessonRepository.getLessonInfo()

        return lessonData.value!!
    }
    fun loadLessonData(choice: Int): List<LessonModel> {
        if (choice == 0) {
            lessonData.value = lessonRepository.getBasicLessonInfo()
        } else if (choice == 1) {
            lessonData.value = lessonRepository.getIntermediateLessonInfo()
        } else if (choice == 2) {
            lessonData.value = lessonRepository.getAdvancedLessonInfo()
        }

        return lessonData.value!!
    }
    fun loadSubLessonData(level: Int): List<DestinationFragment> {
        subLessonData.value = lessonRepository.getSubLessonInfo(level)

        return subLessonData.value!!
    }
}
