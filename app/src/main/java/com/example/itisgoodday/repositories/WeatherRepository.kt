package com.example.itisgoodday.repositories

import com.example.itisgoodday.models.ErrorWeather
import com.example.itisgoodday.models.Weather
import com.example.itisgoodday.network.ApiEndPoints
import com.example.itisgoodday.tools.Either
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit

class WeatherRepository (var apiEndPoints: ApiEndPoints) : IWeatherRepository {
    override suspend fun getWeatherData(lat: String, long: String): Either<ErrorWeather, Weather> {
        var either : Either<ErrorWeather, Weather>? = null
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiEndPoints.getWeather()
            withContext(Dispatchers.IO) {
                either = try {
                    if (response.isSuccessful) {
                        var weather = response.body()
                        Either.Success(weather!!)
                    } else {
                        Either.Error(ErrorWeather.LOAD_ERROR)
                    }
                } catch (e: HttpException) {
                    Either.Error(ErrorWeather.LOAD_ERROR)
                } catch (e: Throwable) {
                    Either.Error(ErrorWeather.LOAD_ERROR)
                }
            }
        }.join()

        return either!!
    }
}