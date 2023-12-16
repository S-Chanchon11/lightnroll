package com.example.light

import com.example.light.practice.viewmodel.PracticeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        PracticeViewModel()
    }
}
