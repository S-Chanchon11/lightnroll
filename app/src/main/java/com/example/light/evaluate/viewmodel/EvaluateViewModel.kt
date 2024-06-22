package com.example.light.evaluate.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.light.UserManager
import com.example.light.evaluate.model.EvaluateModel
import com.example.light.evaluate.model.EvaluateResultModel
import com.example.light.evaluate.model.EvaluateSongModel
import com.example.light.evaluate.repository.EvaluateRepository
import kotlinx.coroutines.launch

class EvaluateViewModel : ViewModel() {
    val TAG = "EvaluateViewModel"
    private val evaluateRepository = EvaluateRepository()
    private var evaluateData: MutableLiveData<EvaluateModel>? = null
    private var evaluateResultData: MutableLiveData<EvaluateResultModel>? = null
    private var resultData: MutableLiveData<EvaluateResultModel>? = null
    private val evaluateSongData = MutableLiveData<List<EvaluateSongModel>>()
//    private val evaluateRepository = EvaluateRepository()
    private var _data = MutableLiveData<List<EvaluateResultModel>>()
    val data: MutableLiveData<List<EvaluateResultModel>> get() = _data
    init {
        fetchDataFromApi()
    }
    fun getData(): LiveData<EvaluateModel>? {
        evaluateData = evaluateRepository.getServicesApiCall()
        return evaluateData
    }

    fun sendData(data: EvaluateModel): LiveData<EvaluateResultModel>? {
        evaluateResultData = evaluateRepository.sendDataToModel(data)
        return evaluateResultData
    }
    fun getSongChoice(): List<EvaluateSongModel> {
        evaluateSongData.value = evaluateRepository.getSongData()
        return evaluateSongData.value!!
    }
//    fun getResult() : List<EvaluateResultModel>? {
//        val rid = UserManager.getRid()
//        resultData = evaluateRepository.getAPIResults(rid)
//        return resultData
//    }
    fun fetchDataFromApi():LiveData<List<EvaluateResultModel>> {
//        viewModelScope.launch {
        val uid = "DSPlOrYFN6d2e00lOlERTh3riTj1"
        _data = evaluateRepository.getAPIResults(uid)
//        Log.d(TAG,_data.toString())
        return _data

    }
}
