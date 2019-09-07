package com.example.itisgoodday.repositories

import com.example.itisgoodday.models.ErrorWeather
import com.example.itisgoodday.models.Weather
import com.example.itisgoodday.tools.Either
import kotlinx.coroutines.Deferred

interface IWeatherRepository {
    suspend fun getWeatherData(lat : String, long : String) :  Either<ErrorWeather, Weather>
}