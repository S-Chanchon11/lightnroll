package com.example.light.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.light.evaluate.model.EvaluateResultModel

class ProfileViewModel : ViewModel() {

    private val profileRepository = ProfileRepository()
    private var profileData: MutableLiveData<ProfileModel>? = null
    private var resultData: MutableLiveData<List<EvaluateResultModel>>? = null

    fun getData(data: String): LiveData<ProfileModel>? {
        profileData = profileRepository.getServicesApiCall(data)
        return profileData
    }

    fun getResultData(data: String): LiveData<List<EvaluateResultModel>>? {
        resultData = profileRepository.getResult(data)
        return resultData
    }
}
