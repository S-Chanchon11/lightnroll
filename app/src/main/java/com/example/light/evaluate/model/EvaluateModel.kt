package com.example.light.evaluate.model

data class EvaluateModel(
    val rid: String,
    val tempo: Int
)

data class EvaluateResultModel(
    val uid: String,
    val rid: String,
    val score: Double,
    val song_name: String,
    val tempo: Int,
    val detected_tempo: Int,
    val pred_result: List<String>
)

data class EvaluateSongModel(
    val song_name: String,
    val artist: String,
    val chord_sample: String,
    val tempo: Int,
    val key: String,
    val level: String
)

data class EvaluateUpdateModel(
    val rid: String,
    val song_name: String,
    val detected_tempo: Int,
    val pred_result: List<String>?
)

data class PredictionModel(
    val detected_tempo: Int,
    val pred_result: List<String>?
)
