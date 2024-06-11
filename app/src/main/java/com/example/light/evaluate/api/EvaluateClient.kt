package com.example.light.evaluate.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object EvaluateClient {

    const val MainServer = "http://10.0.2.2:5001/lightnroll-11/us-central1/"

    val retrofitClient: Retrofit.Builder by lazy {

        val okhttpClient = OkHttpClient.Builder()

        Retrofit.Builder()
            .baseUrl(MainServer)
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterface: EvaluateAPI by lazy {
        retrofitClient
            .build()
            .create(EvaluateAPI::class.java)
    }
}
