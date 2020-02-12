package com.project.network.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse (

    /** 첫번째가 오늘날씨 두번째가 내일 날씨 */
    @SerializedName("consolidated_weather")
    var consolidatedWeather: List<WeatherInfo> = emptyList()

) : BaseApiResponse()


data class WeatherInfo(

    @SerializedName("weather_state_name")
    var weatherStateName: String = "",

    @SerializedName("weather_state_abbr")
    var weatherStateAbbr: String = "",

    @SerializedName("the_temp")
    var theTemp: Int = 0,

    @SerializedName("humidity")
    var humidity: Float = 0f

)
