package hr.stjepan.example.weatherapp.domain

import android.util.Log
import androidx.lifecycle.LiveData
import hr.stjepan.example.weatherapp.BuildConfig
import hr.stjepan.example.weatherapp.data.model.WeatherResponse
import hr.stjepan.example.weatherapp.data.model.DayWeatherModel
import hr.stjepan.example.weatherapp.data.model.HourWeatherModel
import hr.stjepan.example.weatherapp.data.remote.MyRetrofitBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {

    var job: CompletableJob? = null
    var job1: CompletableJob? = null
    var job2: CompletableJob? = null

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
                            "f493b6e5ae6e52716a9febb42049aa00",
                            "metric"
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

    fun getWeekWeather(lat: Double, long: Double): LiveData<DayWeatherModel> {
        job1 = Job()
        return object : LiveData<DayWeatherModel>() {
            override fun onActive() {
                super.onActive()
                job1?.let {
                    CoroutineScope(IO + it).launch {
                        val weather = MyRetrofitBuilder.weatherApiService.getWeekWeatherData(
                            lat,
                            long,
                            8,
                            "f493b6e5ae6e52716a9febb42049aa00",
                            "metric"
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

    fun getDayWeather(lat: Double, long: Double): LiveData<HourWeatherModel> {
        Log.e("Stjepan", "das ")
        job2 = Job()
        return object : LiveData<HourWeatherModel>() {
            override fun onActive() {
                super.onActive()
                job2?.let {
                    CoroutineScope(IO + it).launch {
                        val weather = MyRetrofitBuilder.weatherApiService1.getHourWeatherData(
                            lat,
                            long,
                            48,
                            "f493b6e5ae6e52716a9febb42049aa00",
                            "metric"
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
        job2?.cancel()
    }
}