package com.example.light.chord.repository

import com.example.light.chord.model.RecyclerModel

class PracticeRepository2 {

    fun getChord(): List<RecyclerModel> {
        val lst: List<RecyclerModel>
        lst = ArrayList()

        lst.add(RecyclerModel("C"))
        lst.add(RecyclerModel("D"))
        lst.add(RecyclerModel("E"))
        lst.add(RecyclerModel("F"))
        lst.add(RecyclerModel("G"))
        lst.add(RecyclerModel("A"))
        lst.add(RecyclerModel("B"))

        return lst
    }
}
