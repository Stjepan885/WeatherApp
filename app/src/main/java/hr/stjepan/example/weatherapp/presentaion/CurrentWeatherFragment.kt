package hr.stjepan.example.weatherapp.presentaion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
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


class CurrentWeatherFragment : Fragment() {

    lateinit var textHumidity: TextView
    lateinit var textCurrentTime: TextView
    lateinit var textWeatherType: TextView
    lateinit var textCurrentTemperature: TextView
    lateinit var imageIcon: ImageView
    private lateinit var viewMainModel: MainViewModel

    companion object {
        fun newInstance() = CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_current_weather, container, false)

        textCurrentTime = view.findViewById(R.id.textViewCurrentTime)
        textHumidity = view.findViewById(R.id.textViewHumidity)
        textWeatherType = view.findViewById(R.id.textViewWeatherType)
        textCurrentTemperature = view.findViewById(R.id.textViewCurrentTemperature)
        imageIcon = view.findViewById(R.id.imageViewWeatherIcon)

        viewMainModel = ViewModelProvider(activity!!).get(MainViewModel::class.java)

        //checkWeather()

        return view
    }

    private fun checkWeather() {
        viewModel = ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)

        viewModel.weather.observe(viewLifecycleOwner, Observer {
            println("DEBUG: $it")
            setUiText(it)
        })
        viewModel.setLocation(45.814,15.978)
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
        Log.e("image", "url $url")
        Picasso.get().load(url).into(imageIcon)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}