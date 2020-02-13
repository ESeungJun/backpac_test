package com.example.backpactest.model.repository

import com.example.backpactest.model.network.ApiController
import com.example.backpactest.model.network.api.WeatherApiService
import com.project.network.NetworkBinder
import com.project.network.RetrofitClient
import com.project.network.response.BaseApiResponse
import io.reactivex.disposables.CompositeDisposable

class WeatherRepository(private val disposable: CompositeDisposable) {



    fun requestLocationApi(onError: (Throwable) -> Unit, onNext: (List<BaseApiResponse>) -> Unit) {

        NetworkBinder().apply {
            setDisposable(disposable)
            setOnError { onError(it) }
            setOnNextList { onNext(it) }
        }.executeListResponse(ApiController.weatherApiService.searchLocation())
    }

    fun requestWeatherApi(weoId: Int) =
        ApiController.weatherApiService.searchWeatherForLocation(weoId)
}