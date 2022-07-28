package hr.stjepan.example.weatherapp.data.remote

import hr.stjepan.example.weatherapp.data.model.WeatherResponse
import hr.stjepan.example.weatherapp.data.model.WeekWeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/weather")
    suspend fun getWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): WeatherResponse

    @GET("/data/2.5/forecast/daily")
    suspend fun getWeekWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("cnt") count: Int,
        @Query("appid") appid: String
    ): WeekWeatherModel

    @GET("/data/2.5/forecast/hourly")
    suspend fun getHourWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("cnt") count: Int,
        @Query("appid") appid: String
    ): WeekWeatherModel



}