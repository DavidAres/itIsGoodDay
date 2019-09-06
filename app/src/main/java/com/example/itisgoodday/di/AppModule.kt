package com.example.itisgoodday.di

import org.koin.dsl.module

//Koin for application, dependencies for all the fragments in the app
val appModule = module(override = true) {
    //single { GoodDayViewModel(get()) }
    //single { SettingViewModel() }
}