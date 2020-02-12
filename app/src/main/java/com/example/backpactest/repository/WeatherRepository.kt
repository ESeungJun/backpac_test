package com.example.backpactest.repository

import com.example.backpactest.api.WeatherApiService
import com.project.network.NetworkBinder
import com.project.network.RetrofitClient
import com.project.network.response.BaseApiResponse
import com.project.network.response.LocationResponse
import io.reactivex.disposables.CompositeDisposable

class WeatherRepository(private val disposable: CompositeDisposable) {



    fun requestLocationApi(onError: (Throwable) -> Unit, onNext: (List<BaseApiResponse>) -> Unit) {

        NetworkBinder().apply {
            setDisposable(disposable)
            setOnError { onError(it) }
            setOnNextList { onNext(it) }
        }.executeListResponse(RetrofitClient.build().create(WeatherApiService::class.java).searchLocation())
    }

    fun requestWeatherApi(weoId: Int) =
        RetrofitClient.build().create(WeatherApiService::class.java).searchWeatherForLocation(weoId)
}