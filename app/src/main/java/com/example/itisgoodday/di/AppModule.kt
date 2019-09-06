package com.example.itisgoodday.di

import com.example.itisgoodday.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

//Koin for application, dependencies for all the fragments in the app
val appModule = module(override = true) {
    viewModel { HomeViewModel(get(), get()) }
    //single { SettingViewModel() }
}