package com.example.light.evaluate.api

import com.example.light.evaluate.model.EvaluateModel
import com.example.light.evaluate.model.PredictionModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PredictAPI {
    @POST("preprocessing")
    fun sendData(@Body dataModal: EvaluateModel): Call<PredictionModel>
}
