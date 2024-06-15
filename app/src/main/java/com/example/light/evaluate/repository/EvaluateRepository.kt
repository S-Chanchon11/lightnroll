package com.example.light.evaluate.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.light.evaluate.api.EvaluateClient
import com.example.light.evaluate.model.EvaluateModel
import com.example.light.evaluate.model.EvaluateResultModel
import com.example.light.evaluate.model.EvaluateSongModel
import retrofit2.Call
import retrofit2.Callback

class EvaluateRepository {

    val responseData = MutableLiveData<EvaluateModel>()
    val responseData2 = MutableLiveData<EvaluateResultModel>()
    val responseSongData = MutableLiveData<EvaluateSongModel>()
    fun getServicesApiCall(): MutableLiveData<EvaluateModel> {
        val call: Call<EvaluateModel> = EvaluateClient.apiInterface.getData()

        call.enqueue(object : Callback<EvaluateModel> {

            override fun onResponse(
                call: Call<EvaluateModel>,
                response: retrofit2.Response<EvaluateModel>
            ) {
//                Log.v("DEBUG : ", response.body()?.pcp.toString())
                Log.v("DEBUG : ", response.body().toString())
                if (response.isSuccessful) {
                    responseData.value = response.body()
                } else {
                }
            }

            override fun onFailure(call: Call<EvaluateModel>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }
        })

        return responseData
    }
    fun sendDataToModel(data: EvaluateModel): MutableLiveData<EvaluateResultModel> {
        val call: Call<EvaluateResultModel> = EvaluateClient.apiInterface.sendData(data)

        call.enqueue(object : Callback<EvaluateResultModel> {

            override fun onFailure(call: Call<EvaluateResultModel>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<EvaluateResultModel>,
                response: retrofit2.Response<EvaluateResultModel>
            ) {
                Log.v("DEBUG : ", response.body().toString())
                if (response.isSuccessful) {
                    responseData2.value = response.body()
                } else {
                }
            }
        })

        return responseData2
    }
    fun getSongData(): List<EvaluateSongModel> {
        val lst: List<EvaluateSongModel>
        lst = ArrayList()

        lst.add(
            EvaluateSongModel(
                "All of Me",
                "John Legend",
                "Em C G D Am",
                120,
                "Fm",
                "Beginner"
            )
        )
        lst.add(
            EvaluateSongModel(
                "Love Story",
                "John Legend",
                "Em C G D Am",
                120,
                "Fm",
                "Beginner"
            )
        )
        lst.add(
            EvaluateSongModel(
                "11:11",
                "John Legend",
                "Em C G D Am",
                120,
                "Fm",
                "Beginner"
            )
        )
        return lst
    }
}
