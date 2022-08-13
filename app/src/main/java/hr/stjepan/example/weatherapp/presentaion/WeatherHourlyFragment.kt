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
import hr.stjepan.example.weatherapp.presentaion.viewModel.WeatherViewModel

class WeatherHourlyFragment : Fragment() {

    private lateinit var adapter: WeatherHourAdapter
    var itemArrayList: ArrayList<Hour> = ArrayList()

    private lateinit var weatherViewModel: WeatherViewModel

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

        weatherViewModel = ViewModelProvider(activity!!).get(WeatherViewModel::class.java)

        weatherViewModel.dayWeather.observe(viewLifecycleOwner, Observer {
            itemArrayList.clear()
            itemArrayList.addAll(it.listHour)
            //itemArrayList.removeAt(0)
            //Log.e("Stjepan vure" , "$it")
            //Log.e("Stjpan vure", " ${itemArrayList.size}")

            Log.e("Hourly ", " ${itemArrayList.size}")

            recyclerView.adapter = adapter
        })

        recyclerView.adapter = adapter
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}