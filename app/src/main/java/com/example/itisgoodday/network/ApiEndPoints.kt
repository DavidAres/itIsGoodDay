package com.example.itisgoodday.network

import com.example.itisgoodday.data.Weather
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


abstract class ApiEndPoints{

    @GET("/forecast/{api}/{lat},{long}")
    abstract fun getDevices(
        @Field("api") api : String,
        @Field("lat") lat : Float,
        @Field("long") long : Float
    ): Call<Weather>

}