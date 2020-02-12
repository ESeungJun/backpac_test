package com.example.backpactest.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.backpactest.BaseViewModel
import com.example.backpactest.repository.WeatherRepository
import com.example.backpactest.vo.LocalVo
import com.example.backpactest.vo.LocalWeatherVo
import com.example.backpactest.vo.WeatherStatus
import com.example.backpactest.vo.WeatherVo
import com.project.network.response.BaseApiResponse
import com.project.network.response.LocationResponse
import com.project.network.response.WeatherInfo
import com.project.network.response.WeatherResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel : BaseViewModel() {


    private val weatherRepository: WeatherRepository

    private val innerLocationLiveData = MutableLiveData<List<LocalWeatherVo>>()

    private val locationWeatherList = mutableListOf<LocalWeatherVo>()
    private val weatherMap = hashMapOf<String, Pair<WeatherVo, WeatherVo>>()


    init {
        weatherRepository = WeatherRepository(baseDisposable)
    }


    fun getLocationWeatherInfo() {
        weatherRepository.requestLocationApi(this::onError, this::onResponseLocationInfo)
    }

    private fun onResponseLocationInfo(response: List<BaseApiResponse>) {

        val requestList = mutableListOf<Observable<WeatherResponse>>()


        response.forEach {
            val locationResponse = it as LocationResponse
            val localVo = LocalVo(locationResponse.title, locationResponse.woeid)

            locationWeatherList.add(LocalWeatherVo(localVo, null, null))
            requestList.add(weatherRepository.requestWeatherApi(locationResponse.woeid).subscribeOn(Schedulers.io()))
        }


        baseDisposable.add(
            Observable.zip(requestList) { response ->

                val weatherResponse = response as WeatherResponse

                val today = weatherResponse.consolidatedWeather[0]
                val tomorrow = weatherResponse.consolidatedWeather[1]

                val todayVo = WeatherVo(getWeatherStatus(today.weatherStateAbbr), today.theTemp, today.humidity.toInt())
                val tomorrowVo = WeatherVo(getWeatherStatus(tomorrow.weatherStateAbbr), tomorrow.theTemp, tomorrow.humidity.toInt())

                Pair(todayVo, tomorrowVo)

            }.observeOn(AndroidSchedulers.mainThread())
                .subscribe{

                    innerLocationLiveData.postValue()
                }


        )

    }

    private fun getWeatherStatus(weather: String): WeatherStatus {

        when(weather) {
            WeatherStatus.SNOW.status ->{
                return WeatherStatus.SNOW
            }

            WeatherStatus.SLEET.status ->{
                return WeatherStatus.SLEET
            }

            WeatherStatus.HAIL.status ->{
                return WeatherStatus.HAIL
            }

            WeatherStatus.THUNDERSTORM.status ->{
                return WeatherStatus.THUNDERSTORM
            }

            WeatherStatus.HEAVY_RAIN.status ->{
                return WeatherStatus.HEAVY_RAIN
            }

            WeatherStatus.LIGHT_RAIN.status ->{
                return WeatherStatus.LIGHT_RAIN
            }

            WeatherStatus.SHOWERS.status ->{
                return WeatherStatus.SHOWERS
            }

            WeatherStatus.HEAVY_CLOUD.status ->{
                return WeatherStatus.HEAVY_CLOUD
            }

            WeatherStatus.LIGHT_CLOUD.status ->{
                return WeatherStatus.LIGHT_CLOUD
            }

            WeatherStatus.CLEAT.status ->{
                return WeatherStatus.CLEAT
            }

            else ->{
                return WeatherStatus.NONE
            }
        }
    }


    private fun getWeatherInfoForLocationList() {

    }

    private fun onError(throwable: Throwable) {

    }



}