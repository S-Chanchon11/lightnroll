package com.example.light.evaluate.api

import com.example.light.evaluate.model.EvaluateModel
import com.example.light.evaluate.model.EvaluateResultModel
import com.example.light.evaluate.model.EvaluateUpdateModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Path

interface EvaluateAPI {
    // on below line we are creating a method to post our data

    @GET("results/{uid}")
    fun getResult(@Path("uid") uid: String): Call<List<EvaluateResultModel>>

    @GET("results/rid/{rid}")
    fun getResultByRid(@Path("rid") rid: String): Call<EvaluateResultModel>

    @GET("get_pcp")
    fun getData(): Call<EvaluateModel>

    @PUT("results")
    fun updateData(@Body dataModal: EvaluateUpdateModel): Call<EvaluateResultModel>
}
