package com.example.itisgoodday.di

import org.koin.dsl.module

val ioModules = module(override = true) {
    //single { WeatherInformationRepository(get()) }
}