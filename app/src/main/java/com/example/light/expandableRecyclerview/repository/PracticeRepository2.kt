package com.example.light.expandableRecyclerview.repository

import com.example.light.expandableRecyclerview.model.RecyclerModel

class PracticeRepository2 {

    fun getChord(): List<RecyclerModel> {
        val lst: List<RecyclerModel>
        lst = ArrayList()

        lst.add(RecyclerModel("C"))
        lst.add(RecyclerModel("D"))
        lst.add(RecyclerModel("E"))

        return lst
    }
}
