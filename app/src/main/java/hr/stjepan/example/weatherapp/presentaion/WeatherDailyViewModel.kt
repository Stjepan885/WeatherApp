package hr.stjepan.example.weatherapp.presentaion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import hr.stjepan.example.weatherapp.data.model.WeekWeatherModel
import hr.stjepan.example.weatherapp.domain.Repository

class WeatherDailyViewModel : ViewModel() {
    private val _weekWeather: MutableLiveData<Pair<Double, Double>> = MutableLiveData()

    val weekWeather: LiveData<WeekWeatherModel> = Transformations
        .switchMap(_weekWeather){
            Repository.getWeekWeather(it.first, it.second)
        }

    fun setWeekLocation(lat: Double, long: Double){
        val location: Pair<Double,Double> = Pair(lat,long)
        val update = location
        if(_weekWeather.value == update){
            return
        }
        _weekWeather.value = update
    }

    fun cancelJobs(){
        Repository.cancelJobs()
    }
}