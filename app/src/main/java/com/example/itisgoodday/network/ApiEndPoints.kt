package com.example.itisgoodday.network

import com.example.itisgoodday.BuildConfig
import com.example.itisgoodday.models.Weather
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.itisgoodday.tools.Constans
import retrofit2.http.Path

interface ApiEndPoints{
    @GET("/forecast/${Constans.APIKEY}/{latLong}")
    suspend fun getWeather(@Path ("latLong") latLong : String): Response<Weather>
}