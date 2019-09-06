package com.example.itisgoodday.data

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("daily")
    var daily : Daily
) {
    val mockWeather = "{\n" +
            "          \"latitude\": 42.3601,\n" +
            "          \"longitude\": -71.0589,\n" +
            "          \"timezone\": \"America/New_York\",\n" +
            "          \"currently\": {\n" +
            "              \"time\": 1509993277,\n" +
            "              \"summary\": \"Drizzle\",\n" +
            "              \"icon\": \"rain\",\n" +
            "              \"nearestStormDistance\": 0,\n" +
            "              \"precipIntensity\": 0.0089,\n" +
            "              \"precipIntensityError\": 0.0046,\n" +
            "              \"precipProbability\": 0.9,\n" +
            "              \"precipType\": \"rain\",\n" +
            "              \"temperature\": 66.1,\n" +
            "              \"apparentTemperature\": 66.31,\n" +
            "              \"dewPoint\": 60.77,\n" +
            "              \"humidity\": 0.83,\n" +
            "              \"pressure\": 1010.34,\n" +
            "              \"windSpeed\": 5.59,\n" +
            "              \"windGust\": 12.03,\n" +
            "              \"windBearing\": 246,\n" +
            "              \"cloudCover\": 0.7,\n" +
            "              \"uvIndex\": 1,\n" +
            "              \"visibility\": 9.84,\n" +
            "              \"ozone\": 267.44\n" +
            "          },\n" +
            "          \"minutely\": {\n" +
            "              \"summary\": \"Light rain stopping in 13 min., starting again 30 min. later.\",\n" +
            "              \"icon\": \"rain\",\n" +
            "              \"data\": [{\n" +
            "                  \"time\": 1509993240,\n" +
            "                  \"precipIntensity\": 0.007,\n" +
            "                  \"precipIntensityError\": 0.004,\n" +
            "                  \"precipProbability\": 0.84,\n" +
            "                  \"precipType\": \"rain\"\n" +
            "              },\n" +
            "            ...\n" +
            "            ]\n" +
            "          },\n" +
            "          \"hourly\": {\n" +
            "              \"summary\": \"Rain starting later this afternoon, continuing until this evening.\",\n" +
            "              \"icon\": \"rain\",\n" +
            "              \"data\": [{\n" +
            "                  \"time\": 1509991200,\n" +
            "                  \"summary\": \"Mostly Cloudy\",\n" +
            "                  \"icon\": \"partly-cloudy-day\",\n" +
            "                  \"precipIntensity\": 0.0007,\n" +
            "                  \"precipProbability\": 0.1,\n" +
            "                  \"precipType\": \"rain\",\n" +
            "                  \"temperature\": 65.76,\n" +
            "                  \"apparentTemperature\": 66.01,\n" +
            "                  \"dewPoint\": 60.99,\n" +
            "                  \"humidity\": 0.85,\n" +
            "                  \"pressure\": 1010.57,\n" +
            "                  \"windSpeed\": 4.23,\n" +
            "                  \"windGust\": 9.52,\n" +
            "                  \"windBearing\": 230,\n" +
            "                  \"cloudCover\": 0.62,\n" +
            "                  \"uvIndex\": 1,\n" +
            "                  \"visibility\": 9.32,\n" +
            "                  \"ozone\": 268.95\n" +
            "              },\n" +
            "            ...\n" +
            "            ]\n" +
            "          },\n" +
            "         \"daily\": {\n" +
            "              \"summary\": \"Mixed precipitation throughout the week, with temperatures falling to 39°F on Saturday.\",\n" +
            "              \"icon\": \"rain\",\n" +
            "              \"data\": [{\n" +
            "                  \"time\": 1509944400,\n" +
            "                  \"summary\": \"Rain starting in the afternoon, continuing until evening.\",\n" +
            "                  \"icon\": \"rain\",\n" +
            "                  \"sunriseTime\": 1509967519,\n" +
            "                  \"sunsetTime\": 1510003982,\n" +
            "                  \"moonPhase\": 0.59,\n" +
            "                  \"precipIntensity\": 0.0088,\n" +
            "                  \"precipIntensityMax\": 0.0725,\n" +
            "                  \"precipIntensityMaxTime\": 1510002000,\n" +
            "                  \"precipProbability\": 0.73,\n" +
            "                  \"precipType\": \"rain\",\n" +
            "                  \"temperatureHigh\": 66.35,\n" +
            "                  \"temperatureHighTime\": 1509994800,\n" +
            "                  \"temperatureLow\": 41.28,\n" +
            "                  \"temperatureLowTime\": 1510056000,\n" +
            "                  \"apparentTemperatureHigh\": 66.53,\n" +
            "                  \"apparentTemperatureHighTime\": 1509994800,\n" +
            "                  \"apparentTemperatureLow\": 35.74,\n" +
            "                  \"apparentTemperatureLowTime\": 1510056000,\n" +
            "                  \"dewPoint\": 57.66,\n" +
            "                  \"humidity\": 0.86,\n" +
            "                  \"pressure\": 1012.93,\n" +
            "                  \"windSpeed\": 3.22,\n" +
            "                  \"windGust\": 26.32,\n" +
            "                  \"windGustTime\": 1510023600,\n" +
            "                  \"windBearing\": 270,\n" +
            "                  \"cloudCover\": 0.8,\n" +
            "                  \"uvIndex\": 2,\n" +
            "                  \"uvIndexTime\": 1509987600,\n" +
            "                  \"visibility\": 10,\n" +
            "                  \"ozone\": 269.45,\n" +
            "                  \"temperatureMin\": 52.08,\n" +
            "                  \"temperatureMinTime\": 1510027200,\n" +
            "                  \"temperatureMax\": 66.35,\n" +
            "                  \"temperatureMaxTime\": 1509994800,\n" +
            "                  \"apparentTemperatureMin\": 52.08,\n" +
            "                  \"apparentTemperatureMinTime\": 1510027200,\n" +
            "                  \"apparentTemperatureMax\": 66.53,\n" +
            "                  \"apparentTemperatureMaxTime\": 1509994800\n" +
            "              },\n" +
            "            ...\n" +
            "            ]\n" +
            "          },\n" +
            "          \"alerts\": [\n" +
            "          {\n" +
            "            \"title\": \"Flood Watch for Mason, WA\",\n" +
            "            \"time\": 1509993360,\n" +
            "            \"expires\": 1510036680,\n" +
            "            \"description\": \"...FLOOD WATCH REMAINS IN EFFECT THROUGH LATE MONDAY NIGHT...\\nTHE FLOOD WATCH CONTINUES FOR\\n* A PORTION OF NORTHWEST WASHINGTON...INCLUDING THE FOLLOWING\\nCOUNTY...MASON.\\n* THROUGH LATE FRIDAY NIGHT\\n* A STRONG WARM FRONT WILL BRING HEAVY RAIN TO THE OLYMPICS\\nTONIGHT THROUGH THURSDAY NIGHT. THE HEAVY RAIN WILL PUSH THE\\nSKOKOMISH RIVER ABOVE FLOOD STAGE TODAY...AND MAJOR FLOODING IS\\nPOSSIBLE.\\n* A FLOOD WARNING IS IN EFFECT FOR THE SKOKOMISH RIVER. THE FLOOD\\nWATCH REMAINS IN EFFECT FOR MASON COUNTY FOR THE POSSIBILITY OF\\nAREAL FLOODING ASSOCIATED WITH A MAJOR FLOOD.\\n\",\n" +
            "            \"uri\": \"http://alerts.weather.gov/cap/wwacapget.php?x=WA1255E4DB8494.FloodWatch.1255E4DCE35CWA.SEWFFASEW.38e78ec64613478bb70fc6ed9c87f6e6\"\n" +
            "          },\n" +
            "          ...\n" +
            "          ],\n" +
            "          {\n" +
            "            \"flags\": {\n" +
            "              \"units\": \"us\",\n" +
            "              ...\n" +
            "            }\n" +
            "          }"
}

enum class ErrorWeather {
    LOAD_ERROR
}