package com.example.light.expandable_list.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.light.expandable_list.model.PracticeModel
import com.example.light.expandable_list.repository.PracticeRepository

class PracticeViewModel : ViewModel() {
    private val practiceRepository = PracticeRepository()
    private val _chord = MutableLiveData<MutableMap<String, PracticeModel>>()
    val practiceDetail: LiveData<MutableMap<String, PracticeModel>> get() = _chord
    val expandableListDetail = HashMap<String, List<String>>()
    fun loadPractice(): HashMap<String, List<String>> {
        _chord.value = practiceRepository.getChord()
        Log.d("ViewModel", _chord.value.toString())

        for (data in practiceRepository.getChord().values) {
            var c_list = ArrayList<String>()
            c_list.add(data.positions.toString())
            c_list.add(data.fingerings.toString())
            expandableListDetail[data.chord] = c_list
        }
        Log.d("ViewModel", expandableListDetail.toString())

        return expandableListDetail
    }

//    fun getPractice(
//        chord: String,
//        s6: String,
//        s5: String,
//        s4: String,
//        s3: String,
//        s2: String,
//        s1: String,
//        f6: String,
//        f5: String,
//        f4: String,
//        f3: String,
//        f2: String,
//        f1: String
//    ) {
//        // _chord.value = Result.success()
//        viewModelScope.launch {
//            getPracticeDataResponseCheck(
//                chord,
//                s6, s5, s4, s3, s2, s1, f6, f5, f4, f3, f2, f1
//            )
//        }
//    }

//    private suspend fun getPracticeDataResponseCheck(
//        chord: String,
//        s6: String,
//        s5: String,
//        s4: String,
//        s3: String,
//        s2: String,
//        s1: String,
//        f6: String,
//        f5: String,
//        f4: String,
//        f3: String,
//        f2: String,
//        f1: String
//    ) {
//        val practiceResponse = practiceRepository.fetchPractice(
//            chord,
//            s6, s5, s4, s3, s2, s1, f6, f5, f4, f3, f2, f1
//        )
//    }
}
