package com.example.itisgoodday.home.interfaces

interface IHomeViewModel {
    fun restoreSetting()
    fun getWeatherInformation(lat : String, long : String)
}