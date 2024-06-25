package com.example.light.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.light.NodeClient
import com.example.light.UserManager
import com.example.light.evaluate.model.EvaluateResultModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileRepository {
    val responseData = MutableLiveData<ProfileModel>()
    val responseResultData = MutableLiveData<List<EvaluateResultModel>>()
    val apiService = NodeClient.profileApiInterface
    val TAG = "ProfileRepository"

    fun getServicesApiCall(data: String): MutableLiveData<ProfileModel> {
        val call: Call<ProfileModel> = NodeClient.profileApiInterface.getProfile(data)

        call.enqueue(object : Callback<ProfileModel> {

            override fun onResponse(
                call: Call<ProfileModel>,
                response: Response<ProfileModel>
            ) {
                if (response.isSuccessful) {
                    responseData.value = response.body()
                } else {
                    Log.d(TAG, "not found")
                }
            }

            override fun onFailure(call: Call<ProfileModel>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }
        })
        return responseData
    }

    fun getUserDetails(): LiveData<ProfileModel> {
        val data = MutableLiveData<ProfileModel>()
        val uid = UserManager.getUid()
//        val call: Call<List<ProfileModel>> = MainClient.profileApiInterface.getProfile(uid)
        if (uid != null) {
            apiService.getProfileNonList(uid).enqueue(object : Callback<ProfileModel> {
                override fun onResponse(call: Call<ProfileModel>, response: Response<ProfileModel>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                        Log.d(TAG, data.value.toString())
                    }
                }

                override fun onFailure(call: Call<ProfileModel>, t: Throwable) {
                }
            })
        }

        return data
    }

    fun getResult(data: String): MutableLiveData<List<EvaluateResultModel>> {
        val call: Call<List<EvaluateResultModel>> = NodeClient.profileApiInterface.getEvaluate(data)

        call.enqueue(object : Callback<List<EvaluateResultModel>> {

            override fun onResponse(
                call: Call<List<EvaluateResultModel>>,
                response: retrofit2.Response<List<EvaluateResultModel>>
            ) {
                if (response.isSuccessful) {
                    responseResultData.value = response.body()
                } else {
                }
            }

            override fun onFailure(call: Call<List<EvaluateResultModel>>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }
        })

        return responseResultData
    }
}
