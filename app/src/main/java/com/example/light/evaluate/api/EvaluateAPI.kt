package com.example.light.evaluate.api

import com.example.light.evaluate.model.EvaluateModel
import com.example.light.evaluate.model.EvaluateResultModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EvaluateAPI {
    // on below line we are creating a method to post our data
    @POST("results")
    fun sendData(@Body dataModal: EvaluateModel): Call<EvaluateResultModel>

    @GET("get_pcp")
    fun getData(): Call<EvaluateModel>
}
