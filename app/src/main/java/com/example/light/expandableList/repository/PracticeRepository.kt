package com.example.light.expandableList.repository

import com.example.light.expandableList.model.Fingerings
import com.example.light.expandableList.model.Positions
import com.example.light.expandableList.model.PracticeModel

class PracticeRepository {

//    suspend fun fetchPractice(
//        chord: String,
//        s6: String,
//        s5: String,
//        s4: String,
//        s3: String,
//        s2: String,
//        s1: String,
//        f6: String,
//        f5: String,
//        f4: String,
//        f3: String,
//        f2: String,
//        f1: String
//    ) = mapOf(
//        "chord" to chord,
//        "positions" to mapOf(
//            "s6" to s6,
//            "s5" to s5,
//            "s4" to s4,
//            "s3" to s3,
//            "s2" to s2,
//            "s1" to s1
//        ),
//        "fingerings" to mapOf(
//            "f6" to f6,
//            "f5" to f5,
//            "f4" to f4,
//            "f3" to f3,
//            "f2" to f2,
//            "f1" to f1
//        )
//    )
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

//    fun getChord(): MutableList<PracticeModel> {
//        // Fetch user data from a remote data source or database
//        var lst : MutableList<PracticeModel> = ArrayList()
//
//        lst.add(PracticeModel(
//            "C",
//            hashMapOf(
//            "s6" to "x",
//            "s5" to "3",
//            "s4" to "2",
//            "s3" to "0",
//            "s2" to "1",
//            "s1" to "0"
//        ),
//            hashMapOf(
//            "f6" to "0",
//            "f5" to "3",
//            "f4" to "2",
//            "f3" to "0",
//            "f2" to "1",
//            "f1" to "0"
//        )))
//        lst.add(PracticeModel(
//            "D",
//            hashMapOf(
//                "s6" to "x",
//                "s5" to "1",
//                "s4" to "2",
//                "s3" to "3",
//                "s2" to "4",
//                "s1" to "5"
//            ),
//            hashMapOf(
//                "f6" to "6",
//                "f5" to "5",
//                "f4" to "4",
//                "f3" to "3",
//                "f2" to "2",
//                "f1" to "1"
//            )))
//        lst.add(PracticeModel(
//            "E",
//            hashMapOf(
//                "s6" to "x",
//                "s5" to "x",
//                "s4" to "x",
//                "s3" to "x",
//                "s2" to "x",
//                "s1" to "x"
//            ),
//            hashMapOf(
//                "f6" to "a",
//                "f5" to "3",
//                "f4" to "1",
//                "f3" to "7",
//                "f2" to "3",
//                "f1" to "5"
//            )))
//
//        return lst
//    }

//    suspend fun positions(
//         s6 : String,
//         s5 : String,
//         s4 : String,
//         s3 : String,
//         s2 : String,
//         s1 : String,
//    ) = hashMapOf(
//        "s6" to s6,
//        "s5" to s5,
//        "s4" to s4,
//        "s3" to s3,
//        "s2" to s2,
//        "s1" to s1,
//    )
//
//    suspend fun fingers(
//        f6 : String,
//        f5 : String,
//        f4 : String,
//        f3 : String,
//        f2 : String,
//        f1 : String,
//    ) = hashMapOf(
//        "f6" to f6,
//        "f5" to f5,
//        "f4" to f4,
//        "f3" to f3,
//        "f2" to f2,
//        "f1" to f1,
//    )
}
