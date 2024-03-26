package com.example.light.expandableRecyclerview.repository

import com.example.light.expandableRecyclerview.model.Fingerings
import com.example.light.expandableRecyclerview.model.Positions
import com.example.light.expandableRecyclerview.model.RecyclerModel

class PracticeRepository2 {
//    var pos = Positions("x", "3", "2", "0", "1", "0")
//    var fin = Fingerings("0", "3", "2", "0", "1", "0")
//    heroList.add(RecyclerModel("C", pos, fin))
//    heroList.add(RecyclerModel("D", pos, fin))
//    heroList.add(RecyclerModel("E", pos, fin))

    fun getChord(): List<RecyclerModel> {
        val lst: List<RecyclerModel>
        lst = ArrayList()
        var chord = "C"
        var pos = Positions("x", "3", "2", "0", "1", "0")
        var fin = Fingerings("0", "3", "2", "0", "1", "0")
        lst.add(RecyclerModel(chord, pos, fin))
        chord = "D"
        pos = Positions("x", "x", "0", "1", "3", "2")
        fin = Fingerings("0", "0", "0", "2", "3", "2")
        lst.add(RecyclerModel(chord, pos, fin))
        chord = "E"
        pos = Positions("0", "2", "3", "1", "0", "0")
        fin = Fingerings("0", "2", "2", "1", "0", "0")
        lst.add(RecyclerModel(chord, pos, fin))
        return lst
    }
}
