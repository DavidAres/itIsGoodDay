package com.example.itisgoodday.models

data class Settings(
    var maxTemperature : String = "",
    var minTemperature : String = "",
    var rainyDay : Boolean = false,
    var windyDay : Boolean = false,
    var city : City? = null
){
    companion object {
        val mockCities = "{\"cities\" : [  \n" +
                "    {\"name\":\"Madrid\", \"long\":\"-3.703582\", \"lat\":\"40.416705\"},  \n" +
                "    {\"name\":\"Amsterdam\", \"long\":\"4.897976\", \"lat\":\"52.37454\"},  \n" +
                "    {\"name\":\"London\", \"long\":\"-0.127647\", \"lat\":\"51.507322\"},  \n" +
                "    {\"name\":\"Paris \", \"long\":\"13.38886\", \"lat\":\"52.517037\"},  \n" +
                "    {\"name\":\"Berlin \", \"long\":\"2.351462\", \"lat\":\"48.856697\"}  \n" + "]}  "

    }
}

enum class ErrorSettings{
    EMPTY_SETTINGS,
    LOAD_ERROR_SETTINGS,
    SAVE_ERROR
}