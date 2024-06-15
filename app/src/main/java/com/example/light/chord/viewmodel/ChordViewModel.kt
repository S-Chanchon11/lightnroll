package com.example.light.chord.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.light.chord.model.PracticeModel
import com.example.light.chord.repository.ChordRepository

class ChordViewModel : ViewModel() {
    private val chordRepository = ChordRepository()
    private val _chord = MutableLiveData<MutableMap<String, PracticeModel>>()
    val practiceDetail: LiveData<MutableMap<String, PracticeModel>> get() = _chord
    val expandableListDetail = HashMap<String, List<String>>()
    val expandableListDetail2 = HashMap<String, List<String>>()
    fun loadPracticePos(): HashMap<String, List<String>> {
        _chord.value = chordRepository.getChord()
        Log.d("ViewModel", _chord.value.toString())

        for (data in chordRepository.getChord().values) {
            var c_list = ArrayList<String>()

            c_list.add(data.positions.s6)
            c_list.add(data.positions.s5)
            c_list.add(data.positions.s4)
            c_list.add(data.positions.s3)
            c_list.add(data.positions.s2)
            c_list.add(data.positions.s1)
            expandableListDetail[data.chord] = c_list
        }
        Log.d("ViewModel", expandableListDetail.toString())

        return expandableListDetail
    }

    fun loadPracticeFin(): HashMap<String, List<String>> {
        _chord.value = chordRepository.getChord()
        Log.d("ViewModel", _chord.value.toString())

        for (data in chordRepository.getChord().values) {
            var c_list = ArrayList<String>()
            c_list.add(data.fingerings.f6)
            c_list.add(data.fingerings.f5)
            c_list.add(data.fingerings.f4)
            c_list.add(data.fingerings.f3)
            c_list.add(data.fingerings.f2)
            c_list.add(data.fingerings.f1)
            expandableListDetail2[data.chord] = c_list
        }
        Log.d("ViewModel", expandableListDetail2.toString())

        return expandableListDetail2
    }

}
