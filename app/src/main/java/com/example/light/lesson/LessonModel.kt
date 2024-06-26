package com.example.light.lesson

import androidx.fragment.app.Fragment

data class LessonModel(
    val image: Int,
    val title: String,
    val description: String
)

data class DestinationFragment(
    val fragment: Fragment,
    val filename: String
)
