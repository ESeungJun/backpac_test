package com.example.backpactest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.backpactest.BaseViewModel
import com.example.backpactest.model.repository.WeatherRepository
import com.example.backpactest.model.vo.LocalVo
import com.example.backpactest.model.vo.LocalWeatherVo
import com.example.backpactest.model.vo.WeatherStatus
import com.example.backpactest.model.vo.WeatherVo
import com.project.network.response.BaseApiResponse
import com.example.backpactest.model.network.response.LocationResponse
import com.example.backpactest.model.network.response.WeatherResponse
import com.project.utils.Logger
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel : BaseViewModel() {


    private val weatherRepository: WeatherRepository

    private val innerLocationLiveData = MutableLiveData<List<LocalWeatherVo>>()
    val locationWeatherLiveData: LiveData<List<LocalWeatherVo>>
        get() = innerLocationLiveData

    private val innerErrorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable>
        get() = innerErrorLiveData

    private val locationWeatherList = mutableListOf<LocalWeatherVo>()


    init {
        weatherRepository = WeatherRepository(baseDisposable)
    }


    fun getLocationWeatherInfo() {
        weatherRepository.requestLocationApi(this::onError, this::onResponseLocationInfo)
    }

    private fun onResponseLocationInfo(response: List<BaseApiResponse>) {

        locationWeatherList.clear()

        val requestList = mutableListOf<Observable<WeatherResponse>>()


        response.forEach {
            val locationResponse = it as LocationResponse
            val localVo = LocalVo(locationResponse.title, locationResponse.woeid)

            locationWeatherList.add(LocalWeatherVo(localVo, null, null))
            requestList.add(weatherRepository.requestWeatherApi(locationResponse.woeid).subscribeOn(Schedulers.io()))
        }


        baseDisposable.add(
            Observable.zip(requestList) { response ->

                response.forEachIndexed { index, any ->
                    val weatherResponse = any as WeatherResponse

                    val today = weatherResponse.consolidatedWeather[0]
                    val tomorrow = weatherResponse.consolidatedWeather[1]

                    val todayVo = WeatherVo(getWeatherStatus(today.weatherStateAbbr), today.theTemp.toInt(), today.humidity.toInt())
                    val tomorrowVo = WeatherVo(getWeatherStatus(tomorrow.weatherStateAbbr), tomorrow.theTemp.toInt(), tomorrow.humidity.toInt())

                    locationWeatherList[index].todayWeather = todayVo
                    locationWeatherList[index].tomorrowWeather = tomorrowVo
                }

            }.observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    locationWeatherList.add(0, LocalWeatherVo(null, null, null))
                    innerLocationLiveData.postValue(locationWeatherList)
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


    private fun onError(throwable: Throwable) {
        innerErrorLiveData.postValue(throwable)
    }

}