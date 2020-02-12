package com.example.backpactest.api

import com.project.network.response.BaseApiResponse
import io.reactivex.Single
import retrofit2.http.*


interface WeatherApiService {

    @POST("")
    fun testPost(@Body body: Any): Single<BaseApiResponse>
}
