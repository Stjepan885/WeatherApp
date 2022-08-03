package hr.stjepan.example.weatherapp.data.remote

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofitBuilder {

    private const val BASE_URL = "https://api.openweathermap.org"
    private const val URL = "https://pro.openweathermap.org"
    private val client = OkHttpClient.Builder().build()

    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
    }

    val weatherApiService: WeatherApi by lazy {
        retrofitBuilder
            .build()
            .create(WeatherApi::class.java)
    }

    val retrofitBuilder1: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
    }

    val weatherApiService1: WeatherApi by lazy {
        retrofitBuilder1
            .build()
            .create(WeatherApi::class.java)
    }
}