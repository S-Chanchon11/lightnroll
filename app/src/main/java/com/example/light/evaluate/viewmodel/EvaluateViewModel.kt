package com.example.light.evaluate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.light.evaluate.model.EvaluateModel
import com.example.light.evaluate.model.EvaluateResultModel
import com.example.light.evaluate.model.EvaluateSongModel
import com.example.light.evaluate.repository.EvaluateRepository

class EvaluateViewModel : ViewModel() {
    private val evaluateRepository = EvaluateRepository()
    private var evaluateData: MutableLiveData<EvaluateModel>? = null
    private var evaluateResultData: MutableLiveData<EvaluateResultModel>? = null
    private val evaluateSongData = MutableLiveData<List<EvaluateSongModel>>()
//    private val evaluateRepository = EvaluateRepository()

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
}
