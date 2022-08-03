package hr.stjepan.example.weatherapp.presentaion.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import hr.stjepan.example.weatherapp.data.model.DayWeatherModel
import hr.stjepan.example.weatherapp.data.model.HourWeatherModel
import hr.stjepan.example.weatherapp.domain.Repository

class WeatherHourlyViewModel : ViewModel() {
   private val _dayWeather: MutableLiveData<Pair<Double, Double>> = MutableLiveData()


    val dayWeather: LiveData<HourWeatherModel> = Transformations
        .switchMap(_dayWeather) {
            Log.e("Stjepan", "$it ")
            Repository.getDayWeather(it.first, it.second)
        }


    fun setDayLocation(lat: Double, long: Double) {
        Log.e("Stjepan", "$lat ")
        val location: Pair<Double, Double> = Pair(lat, long)
        val update = location
        if (_dayWeather.value == update) {
            return
        }
        _dayWeather.value = update
    }

}