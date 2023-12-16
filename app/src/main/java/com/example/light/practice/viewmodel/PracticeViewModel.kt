package com.example.light.practice.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.light.practice.model.PracticeModel
import com.example.light.practice.repository.PracticeRepository
import kotlinx.coroutines.launch

class PracticeViewModel : ViewModel() {
    private val practiceRepository = PracticeRepository()
    private val _chord = MutableLiveData<PracticeModel>()
    val practiceDetail: LiveData<PracticeModel> get() = _chord
    // val chord: LiveData<PracticeModel> = _chord

    fun loadPractice() {
        val chord_mock_up = practiceRepository.getChord("C")
        // Log.d("ViewModel",chord_mock_up.toString())
        _chord.value = chord_mock_up
    }

    fun getPractice(
        chord: String,
        s6: String,
        s5: String,
        s4: String,
        s3: String,
        s2: String,
        s1: String,
        f6: String,
        f5: String,
        f4: String,
        f3: String,
        f2: String,
        f1: String
    ) {
        // _chord.value = Result.success()
        viewModelScope.launch {
            getPracticeDataResponseCheck(
                chord,
                s6, s5, s4, s3, s2, s1, f6, f5, f4, f3, f2, f1
            )
        }
    }

    private suspend fun getPracticeDataResponseCheck(
        chord: String,
        s6: String,
        s5: String,
        s4: String,
        s3: String,
        s2: String,
        s1: String,
        f6: String,
        f5: String,
        f4: String,
        f3: String,
        f2: String,
        f1: String
    ) {
        val practiceResponse = practiceRepository.fetchPractice(
            chord,
            s6, s5, s4, s3, s2, s1, f6, f5, f4, f3, f2, f1
        )
    }
}
