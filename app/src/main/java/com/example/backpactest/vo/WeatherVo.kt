package com.example.backpactest.vo

data class LocalWeatherVo (
    var localName: String = "",
    var todayWeather: WeatherVo,
    var tomorrowWeather: WeatherVo
)


data class WeatherVo (
    var weatherStatus: WeatherStatus,
    var weatherIconUrl: String = ""
)