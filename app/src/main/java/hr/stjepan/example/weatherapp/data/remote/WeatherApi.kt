package hr.stjepan.example.weatherapp.data.remote

import hr.stjepan.example.weatherapp.data.model.WeatherResponse
import hr.stjepan.example.weatherapp.data.model.DayWeatherModel
import hr.stjepan.example.weatherapp.data.model.HourWeatherModel
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
        @Query("appid") appid: String,
        @Query("units") units: String
    ): DayWeatherModel

    @GET("/data/2.5/forecast/hourly")
    suspend fun getHourWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("cnt") count: Int,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): HourWeatherModel
/*
    @GET("/data/2.5/forecast/daily")
    suspend fun getWeekWeatherDataByCityId(
        @Query("id") cityId: Long,
        @Query("cnt") count: Int,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): DayWeatherModel

    @GET("/data/2.5/forecast/hourly")
    suspend fun getHourWeatherDataByCityId(
        @Query("id") cityId: Long,
        @Query("cnt") count: Int,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): DayWeatherModel


 */
}