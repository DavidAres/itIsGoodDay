package com.example.itisgoodday.network

import com.example.itisgoodday.BuildConfig
import com.example.itisgoodday.models.Weather
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import retrofit2.http.Path


interface ApiEndPoints{
    @GET("/forecast/b72d5148071181ee327ef946a4ada0d8/37.8267,-122.4233")
    suspend fun getWeather(): Response<Weather>
}