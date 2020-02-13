package com.example.backpactest.model.network.api

import com.example.backpactest.model.network.response.LocationResponse
import com.example.backpactest.model.network.response.WeatherResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface WeatherApiService {

    @GET("location/search/?query=se")
    fun searchLocation(): Single<List<LocationResponse>>

    @GET("location/{woeId}")
    fun searchWeatherForLocation(@Path("woeId") woeId: Int): Observable<WeatherResponse>
}
