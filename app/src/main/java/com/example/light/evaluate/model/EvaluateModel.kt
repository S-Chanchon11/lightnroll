package com.example.light.evaluate.model

data class EvaluateModel(
    val uid: String,
    val pcp: List<Double>,
    val sample_rate: Int
)

data class EvaluateResultModel(
    val score: Int,
    val prediction_result: List<String>
)

data class EvaluateSongModel(
    val song_name: String,
    val artist: String,
    val chord_sample: String,
    val tempo: Int,
    val key: String,
    val level: String
)
