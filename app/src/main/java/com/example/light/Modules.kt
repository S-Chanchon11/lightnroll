package com.example.light

import com.example.light.expandableList.viewmodel.PracticeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        PracticeViewModel()
    }
}
