package com.example.backpactest.api

import com.project.network.response.BaseApiResponse
import com.project.network.response.LocationResponse
import com.project.network.response.WeatherResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface WeatherApiService {

    @GET("/location/search/?query=se")
    fun searchLocation(): Single<List<LocationResponse>>

    @GET("/location/{woeId}")
    fun searchWeatherForLocation(@Path("woeId") woeId: Int): Observable<WeatherResponse>
}
