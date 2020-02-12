package com.example.backpactest.api

import com.project.network.response.LocationResponse
import com.project.network.response.WeatherResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface WeatherApiService {

    @GET("/location/search/?query=se")
    fun searchLocation(): Single<List<LocationResponse>>

    @GET("/location/{woeid}")
    fun searchWeatherForLocation(@Path("woeid") location: String): Single<WeatherResponse>
}
