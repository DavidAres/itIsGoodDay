package com.example.itisgoodday.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.itisgoodday.models.ErrorSettings
import com.example.itisgoodday.models.ErrorWeather
import com.example.itisgoodday.models.Settings
import com.example.itisgoodday.models.Weather
import com.example.itisgoodday.repositories.WeatherRepository
import com.example.itisgoodday.home.interfaces.IHomeViewModel
import com.example.itisgoodday.tools.Constans.Companion.SAVE_SETTINGS
import com.example.itisgoodday.tools.Either
import com.example.itisgoodday.tools.PreferencesManager
import com.google.gson.Gson
import kotlinx.coroutines.*

class HomeViewModel (var context: Context, var weatherRepository: WeatherRepository) : ViewModel(), IHomeViewModel {
    private var weatherLiveData = MutableLiveData<Either<ErrorWeather, Weather>>()
    private var restoreSettingsLiveData = MutableLiveData<Either<ErrorSettings, Settings>>()
    private var statusDayLiveData = MutableLiveData<Boolean>()
    lateinit var localSettings: Settings

    val stateWeather : LiveData<Either<ErrorWeather, Weather>>
        get() = weatherLiveData
    val stateSettings : LiveData<Either<ErrorSettings, Settings>>
        get() = restoreSettingsLiveData
    val stateDay : LiveData<Boolean>
        get() = statusDayLiveData

    override fun restoreSetting() {
        var settings = PreferencesManager.restoreString(context, SAVE_SETTINGS )
        val gson = Gson()
        var saveSettings = gson.fromJson(settings, Settings::class.java)
        restoreSettingsLiveData.postValue(
            if (settings.isNullOrEmpty())
                Either.Error(ErrorSettings.EMPTY_SETTINGS)
            else {
                localSettings = saveSettings
                Either.Success(saveSettings)
            })
    }

    override fun getWeatherInformation(lat: String, long: String) {
        CoroutineScope(Dispatchers.Default).launch {
            weatherLiveData.postValue((weatherRepository.getWeatherData(lat, long)))
        }
    }

    override fun calculateDay(weather: Weather) {
        var dailyData = weather.daily.data[0]
        var matchCount : Int = 0
        if (dailyData.maxTemperature - localSettings.maxTemperature.toInt() < 5)
            matchCount++
        if (dailyData.minTemperature - localSettings.minTemperature.toInt() < 5)
            matchCount++
        if (dailyData.rainProbability > 1 && localSettings.rainyDay)
            matchCount++
        if (dailyData.windSpeed > 1 && localSettings.windyDay)
            matchCount++

        statusDayLiveData.postValue(matchCount >= 2)
    }


}