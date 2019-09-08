package com.example.itisgoodday.home.interfaces

import com.example.itisgoodday.models.Weather

interface IHomeViewModel {
    fun restoreSetting()
    fun getWeatherInformation(lat : String, long : String)
    fun calculateDay(weather: Weather)
}