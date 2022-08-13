package hr.stjepan.example.weatherapp.presentaion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import hr.stjepan.example.weatherapp.R
import hr.stjepan.example.weatherapp.data.model.WeatherResponse
import hr.stjepan.example.weatherapp.presentaion.viewModel.CurrentWeatherViewModel
import hr.stjepan.example.weatherapp.presentaion.viewModel.MainViewModel
import hr.stjepan.example.weatherapp.presentaion.viewModel.SearchViewModel
import hr.stjepan.example.weatherapp.presentaion.viewModel.WeatherViewModel


class CurrentWeatherFragment : Fragment() {

    lateinit var textHumidity: TextView
    lateinit var textWeatherType: TextView
    lateinit var textCurrentTemperature: TextView
    lateinit var imageIcon: ImageView

    private lateinit var viewModel: CurrentWeatherViewModel
    private lateinit var viewMainModel: MainViewModel
    private lateinit var searchViewModel: SearchViewModel

    //weather view model
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_current_weather, container, false)

        textHumidity = view.findViewById(R.id.textViewHumidity)
        textWeatherType = view.findViewById(R.id.textViewWeatherType)
        textCurrentTemperature = view.findViewById(R.id.textViewCurrentTemperature)
        imageIcon = view.findViewById(R.id.imageViewWeatherIcon)

        viewMainModel = ViewModelProvider(activity!!).get(MainViewModel::class.java)
        viewModel = ViewModelProvider(activity!!).get(CurrentWeatherViewModel::class.java)
        searchViewModel = ViewModelProvider(activity!!).get(SearchViewModel::class.java)

        //weather view model
        weatherViewModel = ViewModelProvider(activity!!)[WeatherViewModel::class.java]

        searchViewModel.selectedCity.observe(viewLifecycleOwner, Observer {
            //viewModel.setLocation(it.coords.lat, it.coords.lon)
            weatherViewModel.setLocation(it.coords.lat, it.coords.lon)
        })

        weatherViewModel.currentWeather.observe(viewLifecycleOwner, Observer {
            setUiText(it)
        })

        viewModel.weather.observe(viewLifecycleOwner, Observer {
            setUiText(it)
        })

        return view
    }

    private fun setUiText(it: WeatherResponse) {
        val temperature = it.temp.temp

        textCurrentTemperature.text = temperature.toInt().toString() + "°"
        textHumidity.text = "HUMIDITY " + it.temp.humidity.toString() + "%"
        textWeatherType.text = it.weather[0].weatherDescription

        val time = it.weather[0].weatherIcon.subSequence(2,3)
        val type = it.weather[0].weatherIcon.subSequence(0,2)
        viewMainModel.selectItem(time.toString(), type.toString())

        setIcon(it.weather[0].weatherIcon)
    }

    private fun setIcon(weatherIcon: String) {
        val url = "https://openweathermap.org/img/wn/$weatherIcon@2x.png"
        Picasso.get().load(url).into(imageIcon)
    }

}