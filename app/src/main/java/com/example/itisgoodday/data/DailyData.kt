package com.example.itisgoodday.data

import com.google.gson.annotations.SerializedName

data class DailyData (
    @SerializedName("temperatureHigh")
    var maxTemperature : Float,
    @SerializedName("temperatureMax")
    var minTemperature : Float,
    @SerializedName("precipProbability")
    var rainProbability : Float,
    @SerializedName("precipProbability")
    var windSpeed : Float
)

