package com.example.light.profile

import com.example.light.evaluate.model.EvaluateResultModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProfileAPI {
    @GET("users/{uid}")
    fun getProfile(@Path("uid") uid: String): Call<ProfileModel>

    @GET("users")
    fun getProfileNonList(@Query("uid") uid: String): Call<ProfileModel>

    @GET("results")
    fun getEvaluate(@Query("uid") uid: String): Call<List<EvaluateResultModel>>
}
