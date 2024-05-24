package com.example.light.expandableRecyclerview.model

import android.graphics.drawable.Drawable

data class LessonModel(
//    val image: Drawable,
    val title: String,
    val description: String
)

data class BasicLessonModel(
    val title: String,
    val description: String
)
data class InterLessonModel(
    val title: String,
    val description: String
)
