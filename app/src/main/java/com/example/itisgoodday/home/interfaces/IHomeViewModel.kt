package com.example.itisgoodday.home.interfaces

interface IHomeViewModel {
    fun restoreSetting()
    fun getWeatherInformation(lat : Float, long : Float)
}