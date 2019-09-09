package com.example.itisgoodday.repositories

import com.example.itisgoodday.models.ErrorWeather
import com.example.itisgoodday.models.Weather
import com.example.itisgoodday.network.ApiEndPoints
import com.example.itisgoodday.tools.Either
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import kotlin.coroutines.resume

class WeatherRepository (var apiEndPoints: ApiEndPoints) : IWeatherRepository {
    var either : Either<ErrorWeather, Weather>? = null

    override suspend fun getWeatherData(lat: String, long: String) : Either<ErrorWeather, Weather> = suspendCancellableCoroutine { continuation ->
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiEndPoints.getWeather("$lat,$long")
              if (response.isSuccessful) {
                    var weather = response.body()
                    either = Either.Success(weather!!)
                    continuation.resume(either!!)
                } else {
                      either = Either.Error(ErrorWeather.LOAD_ERROR)
                      continuation.resume(either!!)
                }
            } catch (e: HttpException) {
                either = Either.Error(ErrorWeather.LOAD_ERROR)
                continuation.resume(either!!)
            } catch (e: Throwable) {
                either = Either.Error(ErrorWeather.LOAD_ERROR)
                continuation.resume(either!!)
            }
        }
    }
}