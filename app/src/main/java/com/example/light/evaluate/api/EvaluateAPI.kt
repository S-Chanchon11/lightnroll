package com.example.light.evaluate.api

import com.example.light.evaluate.model.EvaluateModel
import com.example.light.evaluate.model.EvaluateResultModel
import com.example.light.profile.ProfileModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EvaluateAPI {
    // on below line we are creating a method to post our data
    @POST("evaluate")
    fun sendData(@Body dataModal: EvaluateModel): Call<EvaluateResultModel>
    @GET("results/{uid}")
    fun getResult(@Path("uid") uid: String): Call<List<EvaluateResultModel>>
    @GET("get_pcp")
    fun getData(): Call<EvaluateModel>
}
