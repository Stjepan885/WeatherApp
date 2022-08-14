package hr.stjepan.example.weatherapp.presentaion

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import hr.stjepan.example.weatherapp.R
import hr.stjepan.example.weatherapp.data.model.Cities
import hr.stjepan.example.weatherapp.domain.CityAdapter
import hr.stjepan.example.weatherapp.domain.SelectedCityListener
import hr.stjepan.example.weatherapp.presentaion.viewModel.SearchViewModel
import java.io.IOException

class SearchFragment() : Fragment(), SelectedCityListener {

    var itemArrayList: ArrayList<Cities> = ArrayList()
    var displayArrayList: ArrayList<Cities> = ArrayList()
    private lateinit var adapter: CityAdapter
    lateinit var recyclerView: RecyclerView

    var context = this

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel = ViewModelProvider(requireActivity())[SearchViewModel::class.java]
        searchViewModel.selectedItem.observe(viewLifecycleOwner) {
            if (it == "first" && displayArrayList.size > 0) {
                searchViewModel.selectedCity(displayArrayList[0])
            } else {
                onQueryTextChange(it.toString())
            }
        }

        adapter = CityAdapter(requireActivity(), displayArrayList, context)

        recyclerView = view.findViewById<RecyclerView>(R.id.recycler1)
        recyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        val jsonFileString = getJsonDataFromAsset(requireContext())
        val gson = Gson()
        val listPersonType = object : TypeToken<List<Cities>>() {}.type
        val cities: List<Cities> = gson.fromJson(jsonFileString, listPersonType)

        itemArrayList.clear()
        itemArrayList.addAll(cities)
        displayArrayList.addAll(cities)
        itemArrayList.sortWith { lhs, rhs ->
            if (lhs.cityName < rhs.cityName) -1 else 1
        }
        displayArrayList.sortWith { lhs, rhs ->
            if (lhs.cityName < rhs.cityName) -1 else 1
        }

        recyclerView.adapter = adapter
    }

    private fun onQueryTextChange(item: String) {
        if (item.isNotEmpty()) {
            displayArrayList.clear()
            itemArrayList.forEach {
                if (it.cityName.lowercase().contains(item)) {
                    displayArrayList.add(it)
                }
            }
            recyclerView.adapter = adapter
        } else {
            displayArrayList.clear()
            displayArrayList.addAll(itemArrayList)
            recyclerView.adapter = adapter
        }
    }


    private fun getJsonDataFromAsset(context: Context): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open("cities").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    override fun onClick(coords: Cities) {
        searchViewModel.selectedCity(coords)
    }

}