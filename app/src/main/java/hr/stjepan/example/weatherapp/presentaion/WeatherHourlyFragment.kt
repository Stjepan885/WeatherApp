package hr.stjepan.example.weatherapp.presentaion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.stjepan.example.weatherapp.R
import hr.stjepan.example.weatherapp.data.model.Hour
import hr.stjepan.example.weatherapp.domain.WeatherHourAdapter
import hr.stjepan.example.weatherapp.presentaion.viewModel.WeatherDailyViewModel
import hr.stjepan.example.weatherapp.presentaion.viewModel.WeatherHourlyViewModel

class WeatherHourlyFragment : Fragment() {

    private lateinit var adapter: WeatherHourAdapter
    var itemArrayList: ArrayList<Hour> = ArrayList()

    private lateinit var weatherDayViewModel: WeatherHourlyViewModel
    private lateinit var searchViewModel: SearchViewModel

    var context = this

    companion object {
        fun newInstance() = WeatherHourlyFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_weather_hourly, container, false)

        adapter = WeatherHourAdapter(inflater.context, itemArrayList)

        val recyclerView: RecyclerView = rootView.findViewById(R.id.recycler_day)
        recyclerView.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.HORIZONTAL,false)

        weatherDayViewModel = ViewModelProvider(activity!!).get(WeatherHourlyViewModel::class.java)
        searchViewModel = ViewModelProvider(activity!!).get(SearchViewModel::class.java)

        weatherDayViewModel.dayWeather.observe(viewLifecycleOwner, Observer {
            itemArrayList.addAll(it.listHour)
            //itemArrayList.removeAt(0)
            Log.e("Stjepan vure" , "$it")
            Log.e("Stjpan vure", " ${itemArrayList.size}")
            recyclerView.adapter = adapter
        })

        searchViewModel.selectedCity.observe(viewLifecycleOwner, Observer {

            Log.e("Stjepan", "$it")
            weatherDayViewModel.setDayLocation(it.coords.lat,it.coords.lon)
        })

        recyclerView.adapter = adapter
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}