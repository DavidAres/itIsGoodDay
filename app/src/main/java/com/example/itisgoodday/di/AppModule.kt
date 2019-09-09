package com.example.itisgoodday.di

import com.example.itisgoodday.MainActivity
import com.example.itisgoodday.home.HomeFragment
import com.example.itisgoodday.home.HomeViewModel
import com.example.itisgoodday.settings.SettingsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

//Koin for application, dependencies for all the fragments in the app
val appModule = module(override = true) {
    single {HomeFragment()}
    viewModel { HomeViewModel(get(), get()) }
    viewModel { SettingsViewModel(get()) }
}