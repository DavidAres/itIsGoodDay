package com.example.itisgoodday.home.interfaces

import com.example.itisgoodday.models.ErrorSettings
import com.example.itisgoodday.models.Settings
import com.example.itisgoodday.models.ErrorWeather
import com.example.itisgoodday.models.Weather

interface IHomeFragment {
    fun manageSettingsError(error : ErrorSettings)
    fun manageWeatherError(error : ErrorWeather)
    fun saveSettingsAndLoad(settings: Settings)
    fun compareResults(weather: Weather)
}