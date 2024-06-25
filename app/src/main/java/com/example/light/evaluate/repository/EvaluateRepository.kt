package com.example.light.evaluate.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.light.CloudFuncClient
import com.example.light.NodeClient
import com.example.light.evaluate.model.EvaluateModel
import com.example.light.evaluate.model.EvaluateResultModel
import com.example.light.evaluate.model.EvaluateSongModel
import com.example.light.evaluate.model.EvaluateUpdateModel
import com.example.light.evaluate.model.PredictionModel
import retrofit2.Call
import retrofit2.Callback

class EvaluateRepository {
    val TAG = "EvaluateRepository"
    val responseData = MutableLiveData<EvaluateModel>()
    val responseData2 = MutableLiveData<PredictionModel>()
    var responseData3 = MutableLiveData<List<EvaluateResultModel>>()
    var responseData4 = MutableLiveData<EvaluateResultModel>()
    var responseData5 = MutableLiveData<EvaluateResultModel>()
    fun getServicesApiCall(): MutableLiveData<EvaluateModel> {
        val call: Call<EvaluateModel> = NodeClient.evaluateApiInterface.getData()

        call.enqueue(object : Callback<EvaluateModel> {

            override fun onResponse(
                call: Call<EvaluateModel>,
                response: retrofit2.Response<EvaluateModel>
            ) {
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
    fun sendDataToModel(data: EvaluateModel): MutableLiveData<PredictionModel> {
        val call: Call<PredictionModel> = CloudFuncClient.predictApiInterface.sendData(data)

        call.enqueue(object : Callback<PredictionModel> {

            override fun onFailure(call: Call<PredictionModel>, t: Throwable) {
                Log.v("sendDataToModel DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<PredictionModel>,
                response: retrofit2.Response<PredictionModel>
            ) {
                Log.v("sendDataToModel : ", data.toString())
                Log.v("sendDataToModel DEBUG : ", response.body().toString())
                if (response.isSuccessful) {
                    responseData2.value = response.body()
                } else {
                }
            }
        })

        return responseData2
    }
    fun getAPIResults(uid: String): MutableLiveData<List<EvaluateResultModel>> {
        val call: Call<List<EvaluateResultModel>> = NodeClient.evaluateApiInterface.getResult(uid)

        call.enqueue(object : Callback<List<EvaluateResultModel>> {

            override fun onResponse(
                call: Call<List<EvaluateResultModel>>,
                response: retrofit2.Response<List<EvaluateResultModel>>
            ) {
                if (response.isSuccessful) {
                    responseData3.value = response.body()
//                    Log.d("api",responseData3.value.toString())
                } else {
                    Log.d("api", "error")
                }
            }

            override fun onFailure(call: Call<List<EvaluateResultModel>>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }
        })

        return responseData3
    }
    fun getAPIByRID(rid: String): MutableLiveData<EvaluateResultModel> {
        val call: Call<EvaluateResultModel> = NodeClient.evaluateApiInterface.getResultByRid(rid)

        call.enqueue(object : Callback<EvaluateResultModel> {

            override fun onResponse(
                call: Call<EvaluateResultModel>,
                response: retrofit2.Response<EvaluateResultModel>
            ) {
                if (response.isSuccessful) {
                    responseData4.value = response.body()
//                    Log.d("getAPIByRID",responseData3.value.toString())
                    Log.d("getAPIByRID", response.body().toString())
                } else {
                    Log.d("getAPIByRID", "error")
                }
            }

            override fun onFailure(call: Call<EvaluateResultModel>, t: Throwable) {
                Log.v("getAPIByRID : ", t.message.toString())
            }
        })

        return responseData4
    }
    fun updateResult(body: EvaluateUpdateModel): MutableLiveData<EvaluateResultModel> {
        val call: Call<EvaluateResultModel> = NodeClient.evaluateApiInterface.updateData(body)

        call.enqueue(object : Callback<EvaluateResultModel> {

            override fun onResponse(
                call: Call<EvaluateResultModel>,
                response: retrofit2.Response<EvaluateResultModel>
            ) {
                if (response.isSuccessful) {
                    responseData5.value = response.body()
                    Log.d(TAG, response.body().toString())
                } else {
                    Log.d("api", "error")
                }
            }

            override fun onFailure(call: Call<EvaluateResultModel>, t: Throwable) {
                Log.v("DEBUG updateResult: ", t.message.toString())
            }
        })

        return responseData5
    }

    fun getSongData(): List<EvaluateSongModel> {
        val lst: List<EvaluateSongModel>
        lst = ArrayList()

        lst.add(
            EvaluateSongModel(
                "All of me",
                "John Legend",
                "Em C G D Am",
                120,
                "A",
                "Beginner"
            )
        )
        lst.add(
            EvaluateSongModel(
                "Someone You Loved",
                "Lewis Capaldi",
                "C G Am F",
                110,
                "D",
                "Advanced"
            )
        )
        lst.add(
            EvaluateSongModel(
                "Photograph",
                "Ed Sheeran",
                "C Am G F",
                108,
                "E",
                "Beginner"
            )
        )
        lst.add(
            EvaluateSongModel(
                "Let Her Go",
                "Passenger",
                "C D Em D",
                75,
                "G",
                "Beginner"
            )
        )
        return lst
    }
}
