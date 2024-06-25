package com.example.light.evaluate.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.light.UserManager
import com.example.light.evaluate.model.EvaluateModel
import com.example.light.evaluate.model.EvaluateResultModel
import com.example.light.evaluate.model.EvaluateSongModel
import com.example.light.evaluate.model.EvaluateUpdateModel
import com.example.light.evaluate.model.PredictionModel
import com.example.light.evaluate.repository.EvaluateRepository

class EvaluateViewModel : ViewModel() {
    val TAG = "EvaluateViewModel"
    private val evaluateRepository = EvaluateRepository()
    private var evaluateData: MutableLiveData<EvaluateModel>? = null
    private var evaluateResultData: MutableLiveData<PredictionModel>? = null

    private val evaluateSongData = MutableLiveData<List<EvaluateSongModel>>()

//    private val evaluateRepository = EvaluateRepository()
    private var _data = MutableLiveData<List<EvaluateResultModel>>()
    val data: MutableLiveData<List<EvaluateResultModel>> get() = _data

    private var _dataRID = MutableLiveData<EvaluateResultModel>()

//    val dataRID: MutableLiveData<EvaluateResultModel> get() = _dataRID
    private var updateEvaluateData: MutableLiveData<EvaluateResultModel>? = null
    init {
        fetchDataFromApi()
    }
    fun getData(): LiveData<EvaluateModel>? {
        evaluateData = evaluateRepository.getServicesApiCall()
        return evaluateData
    }

    fun sendData(data: EvaluateModel): LiveData<PredictionModel>? {
        Log.d(TAG, data.toString())

        evaluateResultData = evaluateRepository.sendDataToModel(data)
        return evaluateResultData
    }
    fun getSongChoice(): List<EvaluateSongModel> {
        evaluateSongData.value = evaluateRepository.getSongData()
        return evaluateSongData.value!!
    }
    fun getResultByRID(rid: String): LiveData<EvaluateResultModel> {
//        val rid = UserManager.getRid()
        _dataRID = evaluateRepository.getAPIByRID(rid)
        return _dataRID
    }
    fun fetchDataFromApi(): LiveData<List<EvaluateResultModel>> {
//        viewModelScope.launch {
        val uid = UserManager.getUid()
        _data = uid?.let { evaluateRepository.getAPIResults(it) }!!
        return _data
    }
    fun updateResultByRID(body: EvaluateUpdateModel): LiveData<EvaluateResultModel>? {
        Log.d(TAG,body.toString())
        updateEvaluateData = evaluateRepository.updateResult(body)
        return updateEvaluateData
    }
}



//import android.media.MediaPlayer
//import android.media.MediaRecorder
//import java.io.*
//
//// Function to combine audio files
//fun combineAudioFiles(outputFilePath: String, vararg inputFilePaths: String) {
//    val outputStream = FileOutputStream(outputFilePath)
//    try {
//        for (inputFilePath in inputFilePaths) {
//            val inputStream = FileInputStream(inputFilePath)
//            val buffer = ByteArray(1024)
//            var bytesRead: Int = inputStream.read(buffer)
//            while (bytesRead != -1) {
//                outputStream.write(buffer, 0, bytesRead)
//                bytesRead = inputStream.read(buffer)
//            }
//            inputStream.close()
//        }
//    } catch (e: IOException) {
//        e.printStackTrace()
//    } finally {
//        outputStream.close()
//    }
//}
//
//// Example usage:
//val outputFilePath = "/path/to/output/file.wav"
//val inputFilePath1 = "/path/to/input/file1.wav"
//val inputFilePath2 = "/path/to/input/file2.wav"
//
//combineAudioFiles(outputFilePath, inputFilePath1, inputFilePath2)
