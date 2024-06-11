package com.example.light.evaluate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.light.evaluate.model.EvaluateModel
import com.example.light.evaluate.model.EvaluateResultModel
import com.example.light.evaluate.repository.EvaluateRepository

class EvaluateViewModel : ViewModel() {
    private val evaluateRepository = EvaluateRepository()
    var evaluateData: MutableLiveData<EvaluateModel>? = null
    var evaluateData2: MutableLiveData<EvaluateResultModel>? = null

    fun getData(): LiveData<EvaluateModel>? {
        evaluateData = evaluateRepository.getServicesApiCall()
        return evaluateData
    }

    fun sendData(data: EvaluateModel): LiveData<EvaluateResultModel>? {
        evaluateData2 = evaluateRepository.sendDataToModel(data)
        return evaluateData2
    }
}
