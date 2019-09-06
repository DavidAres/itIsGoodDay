package com.example.itisgoodday.home

interface IHomeViewModel {
    fun restoreSetting()
    fun getWeatherInformation(lat : Int, long : Int)
}