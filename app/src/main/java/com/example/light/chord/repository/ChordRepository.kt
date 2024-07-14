package com.example.light.chord.repository

import com.example.light.chord.model.RecyclerModel

class PracticeRepository2 {

    fun getChordMajor(): List<RecyclerModel> {
        val lst: List<RecyclerModel>
        lst = ArrayList()

        lst.add(RecyclerModel("C Major"))
        lst.add(RecyclerModel("D Major"))
        lst.add(RecyclerModel("E Major"))
        lst.add(RecyclerModel("F Major"))
        lst.add(RecyclerModel("G Major"))
        lst.add(RecyclerModel("A Major"))
        lst.add(RecyclerModel("B Major"))

        return lst
    }

    fun getChordMinor(): List<RecyclerModel> {
        val lst: List<RecyclerModel>
        lst = ArrayList()

        lst.add(RecyclerModel("C Minor"))
        lst.add(RecyclerModel("D Minor"))
        lst.add(RecyclerModel("E Minor"))
        lst.add(RecyclerModel("F Minor"))
        lst.add(RecyclerModel("G Minor"))
        lst.add(RecyclerModel("A Minor"))
        lst.add(RecyclerModel("B Minor"))

        return lst
    }

    fun getChordMajor7(): List<RecyclerModel> {
        val lst: List<RecyclerModel>
        lst = ArrayList()

        lst.add(RecyclerModel("C Major7"))
        lst.add(RecyclerModel("D Major7"))
        lst.add(RecyclerModel("E Major7"))
        lst.add(RecyclerModel("F Major7"))
        lst.add(RecyclerModel("G Major7"))
        lst.add(RecyclerModel("A Major7"))
        lst.add(RecyclerModel("B Major7"))

        return lst
    }
}
