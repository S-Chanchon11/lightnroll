package com.example.light

import android.app.Application
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.google.firebase.FirebaseApp
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
        FirebaseApp.initializeApp(this)
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
    }
}
