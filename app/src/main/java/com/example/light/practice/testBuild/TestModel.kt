package com.example.light.practice.testBuild

class TestModel {

//    private val url = "https://gank.io/api/v2/data/category/Girl/type/Girl/page/2/count/10"
//    private lateinit var listener : OnOkhttpListener
//
//    fun getData(mListener: OnOkhttpListener) {
//        listener = mListener
//        thread {
//            val okHttpClient = OkHttpClient()
//            val request = DownloadManager.Request.Builder().get().url(url).build()
//            okHttpClient.newCall(request).enqueue(object : Callback {
//                override fun onFailure(call: Call, e: IOException) {
//                    listener.onFail(e.message.toString())
//                }
//
//                override fun onResponse(call: Call, response: Response) {
//                    val responseData = response.body?.string()
//                    responseData?.let {
//                        val dataBean = Gson().fromJson(responseData, TestChord::class.java)
//                        listener.onSuccess(dataBean)
//                    }
//
//                }
//
//            })
//        }
//
//    }
//
//    interface OnOkhttpListener {
//        fun onSuccess(bean: TestChord)
//        fun onFail(msg: String)
//    }
}
