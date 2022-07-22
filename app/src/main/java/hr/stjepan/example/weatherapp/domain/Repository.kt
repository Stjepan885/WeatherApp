package hr.stjepan.example.weatherapp.domain

import androidx.lifecycle.LiveData
import hr.stjepan.example.weatherapp.BuildConfig
import hr.stjepan.example.weatherapp.data.model.WeatherResponse
import hr.stjepan.example.weatherapp.data.model.WeekWeatherResponse
import hr.stjepan.example.weatherapp.data.remote.MyRetrofitBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {

    var job: CompletableJob? = null
    var job1: CompletableJob? = null

    fun getWeather(lat: Double, long: Double): LiveData<WeatherResponse> {
        job = Job()
        return object : LiveData<WeatherResponse>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val weather = MyRetrofitBuilder.weatherApiService.getWeatherData(
                            lat,
                            long,
                            BuildConfig.apiKey
                        )
                        withContext(Main) {
                            value = weather
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun getWeekWeather(lat: Double, long: Double): LiveData<WeekWeatherResponse> {
        job1 = Job()
        return object : LiveData<WeekWeatherResponse>() {
            override fun onActive() {
                super.onActive()
                job1?.let {
                    CoroutineScope(IO + it).launch {
                        val weather = MyRetrofitBuilder.weatherApiService.getWeekWeatherData(
                            lat,
                            long,
                            7,
                            BuildConfig.apiKey
                        )
                        withContext(Main) {
                            value = weather
                            it.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs() {
        job?.cancel()
        job1?.cancel()
    }
}