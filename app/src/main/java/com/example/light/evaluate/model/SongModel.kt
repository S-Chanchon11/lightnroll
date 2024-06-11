package com.example.light.evaluate.model

data class SongModel(
    val songId: Int,
    val songName: String,
    val tempo: Int,
    val chords: List<Chord>
)

data class Chord(
    val startTime: Double,
    val endTime: Double,
    val chord: String
)
