package com.example.itisgoodday.di

import com.example.itisgoodday.BuildConfig
import com.example.itisgoodday.network.ApiEndPoints
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Koin module for network connections, generate a singleton for retrofit
val networkModule = module(override = true) {
    single { provideDefaultOkhttpClient() }
    single { provideRetrofit(get())}
    single { provideApiEndPointsServices(get())}
}

fun provideDefaultOkhttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    return builder.build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.DOMINIO)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideApiEndPointsServices(retrofit: Retrofit): ApiEndPoints = retrofit.create(ApiEndPoints::class.java)