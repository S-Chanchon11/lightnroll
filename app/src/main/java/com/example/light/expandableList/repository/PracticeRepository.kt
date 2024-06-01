package com.example.light.expandableList.repository

import com.example.light.expandableList.model.Fingerings
import com.example.light.expandableList.model.Positions
import com.example.light.expandableList.model.PracticeModel

class PracticeRepository {

    fun getChord(): MutableMap<String, PracticeModel> {
        var lst: MutableMap<String, PracticeModel> = mutableMapOf()
        var chord = "C"
        var pos = Positions("x", "3", "2", "0", "1", "0")
        var fin = Fingerings("0", "3", "2", "0", "1", "0")
        lst[chord] = PracticeModel(chord, pos, fin)
        chord = "D"
        pos = Positions("x", "3", "2", "0", "1", "0")
        fin = Fingerings("0", "3", "2", "0", "1", "0")
        lst[chord] = PracticeModel(chord, pos, fin)
        chord = "E"
        pos = Positions("x", "3", "2", "0", "1", "0")
        fin = Fingerings("0", "3", "2", "0", "1", "0")
        lst[chord] = PracticeModel(chord, pos, fin)
        return lst
    }
}
