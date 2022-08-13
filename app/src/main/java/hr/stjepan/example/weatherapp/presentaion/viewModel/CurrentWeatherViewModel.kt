package hr.stjepan.example.weatherapp.presentaion.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import hr.stjepan.example.weatherapp.data.model.WeatherResponse
import hr.stjepan.example.weatherapp.domain.Repository

class CurrentWeatherViewModel : ViewModel() {

    private val _weather: MutableLiveData<Pair<Double, Double>> = MutableLiveData()

    val weather: LiveData<WeatherResponse> = Transformations
        .switchMap(_weather){
            Repository.getWeather(it.first, it.second)
        }

    fun setLocation(lat: Double, long: Double){
        val location: Pair<Double,Double> = Pair(lat,long)
        val update = location
        if(_weather.value == update){
            return
        }
        _weather.value = update
    }

    fun cancelJobs(){
        Repository.cancelJobs()
    }
}