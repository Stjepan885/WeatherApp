package hr.stjepan.example.weatherapp.presentaion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hr.stjepan.example.weatherapp.R
import hr.stjepan.example.weatherapp.data.model.Day
import hr.stjepan.example.weatherapp.domain.WeatherDayAdapter

class WeatherHourlyFragment : Fragment() {

    private lateinit var adapter: WeatherDayAdapter
    var itemArrayList: ArrayList<Day> = ArrayList()

    private lateinit var weatherHourViewModel: WeatherHourlyViewModel

    companion object {
        fun newInstance() = WeatherHourlyFragment()
        var itemArrayList: ArrayList<Day> = ArrayList()
    }

    private lateinit var viewModel: WeatherHourlyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_weather_hourly, container, false)



        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeatherHourlyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}