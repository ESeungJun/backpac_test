package com.example.backpactest.vo


data class LocalWeatherVo(
    var local: LocalVo,
    var todayWeather: WeatherVo?,
    var tomorrowWeather: WeatherVo?
)

data class LocalVo (
    var localName: String = "",
    var woeId: Int = 0
)


data class WeatherVo (
    var weatherStatus: WeatherStatus = WeatherStatus.NONE,
    var temper: Int = 0,
    var humidity: Int = 0
)