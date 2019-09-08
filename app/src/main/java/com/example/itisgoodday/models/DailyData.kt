package com.example.itisgoodday.models

import com.google.gson.annotations.SerializedName

data class DailyData (
    @SerializedName("temperatureHigh")
    var maxTemperature : Float,
    @SerializedName("temperatureLow")
    var minTemperature : Float,
    @SerializedName("precipProbability")
    var rainProbability : Float,
    @SerializedName("windSpeed")
    var windSpeed : Float
)

