package com.example.light

import android.app.Application
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            module {
                viewModelModule
            }
        }
    }
}
