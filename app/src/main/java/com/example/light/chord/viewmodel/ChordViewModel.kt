package com.example.light.chord.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.light.chord.model.RecyclerModel
import com.example.light.chord.repository.PracticeRepository2

class ChordViewModel : ViewModel() {
    private val practiceRepository2 = PracticeRepository2()
    private val data = MutableLiveData<List<RecyclerModel>>()
    fun loadDataMajor(): List<RecyclerModel> {
        data.value = practiceRepository2.getChordMajor()
//        Log.d("VM", data.value.toString())
        return data.value!!
    }
    fun loadDataMinor(): List<RecyclerModel> {
        data.value = practiceRepository2.getChordMinor()
//        Log.d("VM", data.value.toString())
        return data.value!!
    }
    fun loadDataMajor7(): List<RecyclerModel> {
        data.value = practiceRepository2.getChordMajor7()
//        Log.d("VM", data.value.toString())
        return data.value!!
    }
}
