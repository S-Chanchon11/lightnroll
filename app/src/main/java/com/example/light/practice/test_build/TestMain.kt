package com.example.light.practice.test_build

import androidx.appcompat.app.AppCompatActivity


class TestMain : AppCompatActivity() {
//    private lateinit var adapter: TestRecyclerAdapter
//    private var mList = ArrayList<Data>()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        initView()
//        loadData()
//    }
//
//    private fun initView() {
//        val layoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = layoutManager
//        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
//        adapter = TestRecyclerAdapter(mList, this)
//        recyclerView.adapter = adapter
//
//
//    }
//
//    private fun loadData() {
//        val model = OkhttpModel()
//
//        model.getData(object : OkhttpModel.OnOkhttpListener {
//            override fun onSuccess(bean: TestChord) {
//                bean.data.forEach {
//                    mList.add(it)
//                }
//                runOnUiThread {
//                    adapter.notifyDataSetChanged()
//                }
//            }
//
//            override fun onFail(msg: String) {
//                Toast.makeText(this@TestMain, msg, Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
}