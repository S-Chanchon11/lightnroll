package com.example.light.practice.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.light.practice.PracticeAdapterController
import com.example.light.practice.model.PracticeModel
import com.example.light.practice.repository.PracticeRepository
import kotlinx.coroutines.launch

class PracticeViewModel : ViewModel() {
    private val practiceRepository = PracticeRepository()
    private val _chord = MutableLiveData<ArrayList<PracticeModel>>()
    val practiceDetail: LiveData<ArrayList<PracticeModel>> get() = _chord
    //val chord: LiveData<PracticeModel> = _chord

    fun loadPractice() : HashMap<String, List<String>> {
        val expandableListDetail = HashMap<String, List<String>>()
        var c_list = ArrayList<String>()
        //val chord_mock_up = practiceRepository.getChord().chord
        _chord.value = practiceRepository.getChord()
        Log.d("ViewModel", _chord.value.toString())
//        Log.d("ViewModel", _chord.value!![0].chord)
//        Log.d("ViewModel", _chord.value!![0].position["s6"].toString())
//        Log.d("ViewModel", _chord.value!![0].fingerings["f6"].toString())
        //c_list.add(_chord.value!![0].chord)
        _chord.value!!.forEach {
            c_list.add(it.position["s6"].toString())
            Log.d("ViewModel",it.position.toString())
            c_list.add(it.fingerings["f6"].toString())
            Log.d("ViewModel",it.fingerings.toString())
            expandableListDetail[it.chord] = c_list

        }
        Log.d("ViewModel", expandableListDetail.toString())
        return expandableListDetail
    }
    fun getData(): HashMap<String, List<String>> {
        val expandableListDetail = HashMap<String, List<String>>()
        val cricket: MutableList<String> = ArrayList()
        cricket.add("India")
        cricket.add("Pakistan")
        cricket.add("Australia")
        cricket.add("England")
        cricket.add("South Africa")
//        val football: MutableList<String> = ArrayList()
//        football.add("Brazil")
//        football.add("Spain")
//        football.add("Germany")
//        football.add("Netherlands")
//        football.add("Italy")
//        val basketball: MutableList<String> = ArrayList()
//        basketball.add("United States")
//        basketball.add("Spain")
//        basketball.add("Argentina")
//        basketball.add("France")
//        basketball.add("Russia")
        expandableListDetail["CRICKET TEAMS"] = cricket
//        expandableListDetail["FOOTBALL TEAMS"] = football
//        expandableListDetail["BASKETBALL TEAMS"] = basketball
        return expandableListDetail
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
