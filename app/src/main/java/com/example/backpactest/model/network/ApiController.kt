package com.example.backpactest.model.network

import com.example.backpactest.model.network.api.WeatherApiService
import com.project.network.RetrofitClient

object ApiController {

    val weatherApiService
        get() = RetrofitClient.build().create(WeatherApiService::class.java)

}