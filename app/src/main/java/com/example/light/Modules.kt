package com.example.light

import com.example.light.chord.viewmodel.ChordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        ChordViewModel()
    }
}
