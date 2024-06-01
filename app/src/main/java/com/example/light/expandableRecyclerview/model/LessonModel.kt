package com.example.light.expandableRecyclerview.model

import androidx.fragment.app.Fragment

data class LessonModel(
//    val image: Drawable,
    val title: String,
    val description: String
)

data class DestinationFragment(
    val fragment: Fragment,
    val detail: String
)
