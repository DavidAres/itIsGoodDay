package com.example.itisgoodday.models

data class Settings(
    var maxTemperature : String = "",
    var minTemperature : String = "",
    var rainyDay : Boolean = false,
    var windyDay : Boolean = false
)

enum class ErrorSettings{
    EMPTY_SETTINGS,
    LOAD_ERROR_SETTINGS,
    SAVE_ERROR
}