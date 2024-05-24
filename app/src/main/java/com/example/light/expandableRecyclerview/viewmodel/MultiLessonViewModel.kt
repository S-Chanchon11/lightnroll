package com.example.light.expandableRecyclerview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.light.expandableRecyclerview.model.BasicLessonModel
import com.example.light.expandableRecyclerview.model.InterLessonModel

class MultiLessonViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Any>>()
    val items: LiveData<List<Any>> get() = _items

    fun loadItemsBasedOnSelection(selection: String) {
        // Fetch or filter data based on selection
        val newItems = when (selection) {
            "Basic" -> getItemsOfBasicLessonModel()
            "Intermediate" -> getItemsOfInterLessonModel()
            else -> emptyList()
        }
        _items.value = newItems
    }

    private fun getItemsOfBasicLessonModel(): List<BasicLessonModel> {
        // Fetch or generate items of type A
        return listOf(
            BasicLessonModel("a", "Item A1"),
            BasicLessonModel("b", "Item A2")
        )
    }

    private fun getItemsOfInterLessonModel(): List<InterLessonModel> {
        // Fetch or generate items of type B
        return listOf(InterLessonModel("1", "Description B1"), InterLessonModel("2", "Description B2"))
    }
}
