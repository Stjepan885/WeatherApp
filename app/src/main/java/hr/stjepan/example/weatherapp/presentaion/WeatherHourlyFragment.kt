package hr.stjepan.example.weatherapp.presentaion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hr.stjepan.example.weatherapp.R

class WeatherHourlyFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherHourlyFragment()
    }

    private lateinit var viewModel: WeatherHourlyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_hourly, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeatherHourlyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}