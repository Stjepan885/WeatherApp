package hr.stjepan.example.weatherapp.presentaion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val mutableSelectedItem = MutableLiveData<Pair<String, String>>()
    val selectedItem: LiveData<Pair<String,String>> get() = mutableSelectedItem

    fun selectItem(item: String, item1: String) {
        val items = Pair(item,item1)
        mutableSelectedItem.value = items
    }


    /*

    private val _weather: MutableLiveData<Pair<Double, Double>> = MutableLiveData()
    private val _weekWeather: MutableLiveData<Pair<Double, Double>> = MutableLiveData()

    val weather: LiveData<WeatherResponse> = Transformations
        .switchMap(_weather){
            Repository.getWeather(it.first, it.second)
        }

    val weekWeather: LiveData<WeekWeatherResponse> = Transformations
        .switchMap(_weekWeather){
            Repository.getWeekWeather(it.first, it.second)
        }

    fun setLocation(lat: Double, long: Double){
        val location: Pair<Double,Double> = Pair(lat,long)
        val update = location
        if(_weather.value == update){
            return
        }
        _weather.value = update
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

     */
}