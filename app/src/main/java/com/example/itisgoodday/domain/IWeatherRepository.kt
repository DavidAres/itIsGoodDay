package com.example.itisgoodday.domain

import com.example.itisgoodday.data.ErrorWeather
import com.example.itisgoodday.data.Weather
import com.example.itisgoodday.tools.Either
import kotlinx.coroutines.Deferred
import java.util.zip.Deflater

interface IWeatherRepository {
    fun getWeatherData(lat : Long, long : Long) : Deferred<Either<ErrorWeather, Weather>>
}