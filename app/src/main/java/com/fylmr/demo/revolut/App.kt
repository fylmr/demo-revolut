package com.fylmr.demo.revolut

import android.app.Application
import com.fylmr.demo.revolut.di.apiModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(apiModule)
        }
    }

}