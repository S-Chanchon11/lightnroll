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
//            var posobj = Positions(
//                data.positions.s6,
//                data.positions.s5,
//                data.positions.s4,
//                data.positions.s3,
//                data.positions.s2,
//                data.positions.s1
//            )
//            var fingobj = Fingerings(
//                data.fingerings.f6,
//                data.fingerings.f5,
//                data.fingerings.f4,
//                data.fingerings.f3,
//                data.fingerings.f2,
//                data.fingerings.f1
//            )
//            c_list.add(posobj.toString())
//            c_list.add(fingobj.toString())
            // c_list.add(data.fingerings.toString())
            c_list.add(data.positions.s6)
            c_list.add(data.positions.s5)
            c_list.add(data.positions.s4)
            c_list.add(data.positions.s3)
            c_list.add(data.positions.s2)
            c_list.add(data.positions.s1)
//            c_list.add(data.fingerings.f6)
//            c_list.add(data.fingerings.f5)
//            c_list.add(data.fingerings.f4)
//            c_list.add(data.fingerings.f3)
//            c_list.add(data.fingerings.f2)
//            c_list.add(data.fingerings.f1)
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
//            var posobj = Positions(
//                data.positions.s6,
//                data.positions.s5,
//                data.positions.s4,
//                data.positions.s3,
//                data.positions.s2,
//                data.positions.s1
//            )
//            var fingobj = Fingerings(
//                data.fingerings.f6,
//                data.fingerings.f5,
//                data.fingerings.f4,
//                data.fingerings.f3,
//                data.fingerings.f2,
//                data.fingerings.f1
//            )
//            c_list.add(posobj.toString())
//            c_list.add(fingobj.toString())
            // c_list.add(data.fingerings.toString())
//            c_list.add(data.positions.s6)
//            c_list.add(data.positions.s5)
//            c_list.add(data.positions.s4)
//            c_list.add(data.positions.s3)
//            c_list.add(data.positions.s2)
//            c_list.add(data.positions.s1)
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
