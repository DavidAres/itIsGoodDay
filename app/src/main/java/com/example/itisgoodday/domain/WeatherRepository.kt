package com.example.itisgoodday.domain

import com.example.itisgoodday.data.ErrorWeather
import com.example.itisgoodday.data.Weather
import com.example.itisgoodday.tools.Either
import kotlinx.coroutines.Deferred

class WeatherRepository : IWeatherRepository {
    override fun getWeatherData(lat: Long, long: Long): Deferred<Either<ErrorWeather, Weather>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}