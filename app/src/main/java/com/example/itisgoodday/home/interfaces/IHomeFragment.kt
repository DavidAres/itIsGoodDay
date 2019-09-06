package com.example.itisgoodday.home.interfaces

import com.example.itisgoodday.data.ErrorSettings
import com.example.itisgoodday.data.Settings
import com.example.itisgoodday.data.ErrorWeather
import com.example.itisgoodday.data.Weather

interface IHomeFragment {
    fun manageSettingsError(error : ErrorSettings)
    fun manageWeatherError(error : ErrorWeather)
    fun saveSettingsAndLoad(settings: Settings)
    fun compareResults(weather: Weather)
}