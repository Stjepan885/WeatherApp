package hr.stjepan.example.weatherapp.presentaion.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import hr.stjepan.example.weatherapp.data.model.DayWeatherModel
import hr.stjepan.example.weatherapp.data.model.HourWeatherModel
import hr.stjepan.example.weatherapp.domain.Repository

class WeatherDailyViewModel : ViewModel() {
    private val _weekWeather: MutableLiveData<Pair<Double, Double>> = MutableLiveData()
    private val _dayWeather: MutableLiveData<Pair<Double, Double>> = MutableLiveData()

    val weekWeather: LiveData<DayWeatherModel> = Transformations
        .switchMap(_weekWeather) {
            Repository.getWeekWeather(it.first, it.second)
        }

    val dayWeather: LiveData<HourWeatherModel> = Transformations
        .switchMap(_dayWeather) {
            Repository.getDayWeather(it.first, it.second)
        }

    fun setWeekLocation(lat: Double, long: Double) {
        val location: Pair<Double, Double> = Pair(lat, long)
        val update = location
        if (_weekWeather.value == update) {
            return
        }
        _weekWeather.value = update
    }

    fun setDayLocation(lat: Double, long: Double) {

        val location: Pair<Double, Double> = Pair(lat, long)
        val update = location
        if (_dayWeather.value == update) {
            return
        }
        _dayWeather.value = update
    }

    fun setLocation(lat: Double, long: Double) {
        val location: Pair<Double, Double> = Pair(lat, long)
        val update = location
        if (_weekWeather.value == update) {
            return
        }
        _weekWeather.value = update

        if (_dayWeather.value == update) {
            return
        }
        _dayWeather.value = update
    }

}