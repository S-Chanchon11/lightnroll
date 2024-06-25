package com.example.light

import com.example.light.evaluate.api.EvaluateAPI
import com.example.light.profile.ProfileAPI
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NodeClient {

// physical device
    const val NodeServer = "http://192.168.1.38:8888/"
//    var gson = GsonBuilder()
//        .setLenient()
//        .create()
// emulator
//    const val NodeServer = "http://10.0.2.2:8888/"

    val retrofitNodeClient: Retrofit.Builder by lazy {

        val okhttpClient = OkHttpClient.Builder()

        Retrofit.Builder()
            .baseUrl(NodeServer)
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val evaluateApiInterface: EvaluateAPI by lazy {
        retrofitNodeClient
            .build()
            .create(EvaluateAPI::class.java)
    }
    val profileApiInterface: ProfileAPI by lazy {
        retrofitNodeClient
            .build()
            .create(ProfileAPI::class.java)
    }
}
