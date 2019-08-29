package com.hoang.pagedlistexample

import android.app.Application
import com.hoang.pagedlistexample.di.sharableModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    protected open fun startKoin() {
        // DI
        // this check is for RoboElectric tests that run in parallel so Koin gets set up multiple times
        if (GlobalContext.getOrNull() == null) {
            org.koin.core.context.startKoin {
                androidLogger()
                androidContext(this@MyApplication)
                modules(listOf(sharableModule))
            }
        }
    }
}