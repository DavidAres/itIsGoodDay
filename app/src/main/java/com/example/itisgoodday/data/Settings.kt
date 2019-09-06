package com.example.itisgoodday.data

data class Settings(
    var maxTemperature : String,
    var minTemperature : String,
    var rainProbability : String,
    var windSpeed : String
)

enum class ErrorSettings{
    EMPTY_SETTINGS,
    LOAD_ERROR_SETTINGS
}