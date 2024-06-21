package com.example.light

import com.example.light.evaluate.api.EvaluateAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object EvaluateClient {

// physical device
//    const val MainServer = "http://192.168.1.37:5001/lightnroll-11/us-central1/"

// emulator
    const val MainServer = "http://10.0.2.2:8080/"

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
