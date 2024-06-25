package com.example.light

import com.example.light.evaluate.api.PredictAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CloudFuncClient {

    const val CloudServer = "http://192.168.1.38:5050/lightnroll-11/us-central1/"
//    private const val CloudServer = "http://10.0.2.2:5050/"

    private val retrofitCloudFuncClient: Retrofit.Builder by lazy {

        val okhttpClient = OkHttpClient.Builder()

        Retrofit.Builder()
            .baseUrl(CloudServer)
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val predictApiInterface: PredictAPI by lazy {
        retrofitCloudFuncClient
            .build()
            .create(PredictAPI::class.java)
    }
}
