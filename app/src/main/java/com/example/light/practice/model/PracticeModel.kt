package com.example.light.practice.model

data class PracticeModel(
    val chord: String,
    val positions: Positions,
    val fingerings: Fingerings
)

data class Positions(
    val s6: String,
    val s5: String,
    val s4: String,
    val s3: String,
    val s2: String,
    val s1: String
)

data class Fingerings(
    val f6: String,
    val f5: String,
    val f4: String,
    val f3: String,
    val f2: String,
    val f1: String
)

// data class PracticeDetailModel(
//
// )

// data class PositionModel(
//    val s6: String,
//    val s5: String,
//    val s4: String,
//    val s3: String,
//    val s2: String,
//    val s1: String
// )
//
// data class FingeringModel(
//    val f6: String,
//    val f5: String,
//    val f4: String,
//    val f3: String,
//    val f2: String,
//    val f1: String
// )
