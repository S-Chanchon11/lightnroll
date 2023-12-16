package com.example.light.practice.repository

import com.example.light.practice.model.PracticeModel

class PracticeRepository {

    suspend fun fetchPractice(
        chord: String,
        s6: String,
        s5: String,
        s4: String,
        s3: String,
        s2: String,
        s1: String,
        f6: String,
        f5: String,
        f4: String,
        f3: String,
        f2: String,
        f1: String
    ) = mapOf(
        "chord" to chord,
        "positions" to mapOf(
            "s6" to s6,
            "s5" to s5,
            "s4" to s4,
            "s3" to s3,
            "s2" to s2,
            "s1" to s1
        ),
        "fingerings" to mapOf(
            "f6" to f6,
            "f5" to f5,
            "f4" to f4,
            "f3" to f3,
            "f2" to f2,
            "f1" to f1
        )
    )

    fun getChord(chordName: String): PracticeModel {
        // Fetch user data from a remote data source or database
        val position = mapOf(
            "s6" to "x",
            "s5" to "3",
            "s4" to "2",
            "s3" to "0",
            "s2" to "1",
            "s1" to "0"
        )
        val fingering = mapOf(
            "f6" to "0",
            "f5" to "3",
            "f4" to "2",
            "f3" to "0",
            "f2" to "1",
            "f1" to "0"
        )
//        Log.d("Repo",PracticeModel(
//            chordName,
//            position,
//            fingering
//        ).toString()
//        )

        return PracticeModel(
            chordName,
            position,
            fingering
        )
    }

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
