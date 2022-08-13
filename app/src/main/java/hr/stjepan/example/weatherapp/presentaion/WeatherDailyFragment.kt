package hr.stjepan.example.weatherapp.presentaion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.stjepan.example.weatherapp.R
import hr.stjepan.example.weatherapp.data.model.Day
import hr.stjepan.example.weatherapp.domain.WeatherDayAdapter
import hr.stjepan.example.weatherapp.presentaion.viewModel.SearchViewModel
import hr.stjepan.example.weatherapp.presentaion.viewModel.WeatherDailyViewModel

class WeatherDailyFragment : Fragment() {

    private lateinit var adapter: WeatherDayAdapter
    var itemArrayList: ArrayList<Day> = ArrayList()

    private lateinit var weatherDayViewModel: WeatherDailyViewModel
    private lateinit var searchViewModel: SearchViewModel

    var context = this

    companion object {
        fun newInstance() = WeatherDailyFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_weather_hourly, container, false)

        adapter = WeatherDayAdapter(inflater.context, itemArrayList)

        val recyclerView: RecyclerView = rootView.findViewById(R.id.recycler_day)
        recyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

        weatherDayViewModel = ViewModelProvider(activity!!).get(WeatherDailyViewModel::class.java)

        weatherDayViewModel.weekWeather.observe(viewLifecycleOwner, Observer {
            itemArrayList.addAll(it.list)
            itemArrayList.removeAt(0)
            //Log.e("Stjepan" , "$itemArrayList")
            //Log.e("Stjpan", " ${itemArrayList.size}")
            recyclerView.adapter = adapter
        })

        searchViewModel = ViewModelProvider(activity!!).get(SearchViewModel::class.java)

        searchViewModel.selectedCity.observe(viewLifecycleOwner, Observer {
            weatherDayViewModel.setWeekLocation(it.coords.lat,it.coords.lon)
        })

        //Day(day=1659006000, temp=DayTemps(temp=300.9, temp_min=291.9, temp_max=305.39), dayWeather=[DayWeather(type=Clear, description=sky is clear, icon=01d)])
/*
        val dayWeather:ArrayList<DayWeather> = ArrayList()
        dayWeather.add(DayWeather("clear", "sky", "01d"))
        val temp = Tempera(300.9, 291.9, 305.39)

        val item = Day(1659006000, temp,dayWeather)

        itemArrayList.clear()
        itemArrayList.add(item)
        itemArrayList.add(item)
        itemArrayList.add(item)
        itemArrayList.add(item)

 */

        //weatherDayViewModel.setWeekLocation(45.814,15.978)
        recyclerView.adapter = adapter

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(WeatherDailyViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel = ViewModelProvider(activity!!).get(WeatherDailyViewModel::class.java)

    }

}