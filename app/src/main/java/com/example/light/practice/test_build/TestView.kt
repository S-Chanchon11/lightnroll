package com.example.light.practice.test_build

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

class TestView(adapter) {
    private lateinit var adapter: TestRecyclerAdapter
    private var mList = ArrayList<Data>()
    private fun initView() {
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        adapter = TestRecyclerAdapter(mList, this)
        recyclerView.adapter = adapter

    }
}