package com.example.light.practice.test_build

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.light.R

class TestRecyclerAdapter(private val dataList: List<Data>, private val context: Context) : RecyclerView.Adapter<TestRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImg: ImageView = view.findViewById(R.id.iv_img)
        val tvDesc: TextView = view.findViewById(R.id.tv_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean = dataList[position]
        Glide.with(context).load(bean.images[0]).into(holder.ivImg)
        holder.tvDesc.text = bean.desc
    }

    override fun getItemCount() = dataList.size
}