package com.example.light.evaluate.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.light.evaluate.api.EvaluateClient
import com.example.light.evaluate.model.EvaluateModel
import com.example.light.evaluate.model.EvaluateResultModel
import retrofit2.Call
import retrofit2.Callback

class EvaluateRepository {

    val responseData = MutableLiveData<EvaluateModel>()
    val responseData2 = MutableLiveData<EvaluateResultModel>()

    fun getServicesApiCall(): MutableLiveData<EvaluateModel> {
//        val requestBody: RequestBody = RequestBody.create(MediaType.parse(
//            "application/json; charset=utf-8"), rootObject.toString()
//        )
//        val call = EvaluateClient.apiInterface.sendData(requestBody)
//        val dataModal = EvaluateModel(name, result)

        // calling a method to create a post and passing our modal class.
//        val call: Call<EvaluateModel> = EvaluateClient.apiInterface.sendData(dataModal)
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
}

// class StudentRepository {
//
//    val responseData = MutableLiveData<ResponseBody>()
//
//    fun getStudentDetail(con: Context, rootObject: JSONObject): MutableLiveData<ResponseBody> {
//
//        val gson = Gson()
//
//        val retrofit = RetrofitConfig.getRetrofit()
//
//        val requestBody: RequestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), rootObject.toString())
//
//        val service = retrofit?.create(ApiInterface::class.java)
//
//        val call = service?.getStudentDetail(requestBody)
//
//        call?.enqueue(object : Callback<ResponseBody> {
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//
//                if (response.isSuccessful) {
//
//                    responseData.value = response.body()
//
//                }else{
//
//                    responseData.value = null
//
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//
//                responseData.value = null
//
//            }
//        })
//        return responseData
//    }
//
// }
