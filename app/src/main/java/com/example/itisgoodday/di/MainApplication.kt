package com.example.itisgoodday.di

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

//Application that override the main application in order to initialite koin
class MainApplication : Application() {

    companion object {
        lateinit var appContext : Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this@MainApplication
        startKoin {
            androidContext(appContext)
            androidLogger()
            modules(listOf(appModule, networkModule, ioModules))
        }
    }

}