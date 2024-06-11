package com.example.light.evaluate.model

data class EvaluateModel(
    val _uid: String,
    val pcp: List<Double>,
    val sample_rate: Int
)

data class EvaluateResultModel(
    val prediction: String
)
