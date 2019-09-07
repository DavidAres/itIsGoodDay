package com.example.itisgoodday.di

import com.example.itisgoodday.repositories.WeatherRepository
import org.koin.dsl.module

val ioModules = module(override = true) {
    single { WeatherRepository(get()) }
}