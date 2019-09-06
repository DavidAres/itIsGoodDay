package com.example.itisgoodday.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.itisgoodday.MainActivity.Companion.SAVE_SETTINGS
import com.example.itisgoodday.data.ErrorSettings
import com.example.itisgoodday.data.ErrorWeather
import com.example.itisgoodday.data.Settings
import com.example.itisgoodday.data.Weather
import com.example.itisgoodday.domain.WeatherRepository
import com.example.itisgoodday.home.interfaces.IHomeViewModel
import com.example.itisgoodday.network.ApiEndPoints
import com.example.itisgoodday.tools.Either
import com.example.itisgoodday.tools.PreferencesManager
import retrofit2.Retrofit

class HomeViewModel (var context: Context, var weatherRepository: WeatherRepository) : ViewModel(), IHomeViewModel {

    private var weatherLiveData = MutableLiveData<Either<ErrorWeather, Weather>>()
    private var restoreSettingsLiveData = MutableLiveData<Either<ErrorSettings, Settings>>()
    val stateWeather : LiveData<Either<ErrorWeather, Weather>>
        get() = weatherLiveData
    val stateSettings : LiveData<Either<ErrorSettings, Settings>>
        get() = restoreSettingsLiveData

    override fun restoreSetting() {
        var settings = PreferencesManager.restoreString(context, SAVE_SETTINGS )
        if (settings.isNullOrEmpty())
            Either.Error(ErrorSettings.EMPTY_SETTINGS)
        else
            Either.Success(settings)
    }

    override fun getWeatherInformation(lat: Float, long: Float) {
    }
}