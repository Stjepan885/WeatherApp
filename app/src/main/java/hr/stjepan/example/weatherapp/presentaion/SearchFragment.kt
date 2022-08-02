package hr.stjepan.example.weatherapp.presentaion

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import hr.stjepan.example.weatherapp.R
import hr.stjepan.example.weatherapp.data.model.Cities
import hr.stjepan.example.weatherapp.domain.CityAdapter
import hr.stjepan.example.weatherapp.domain.SelectedCityListener
import java.io.IOException

class SearchFragment() : Fragment(), SelectedCityListener {

    private lateinit var adapter: CityAdapter
    var itemArrayList: ArrayList<Cities> = ArrayList()
    var displayArrayList: ArrayList<Cities> = ArrayList()
    lateinit var recyclerView: RecyclerView


    var context = this

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
        viewModel.selectedItem.observe(viewLifecycleOwner, Observer {
            onQueryTextChange(it.toString())
        })

        adapter = CityAdapter(requireActivity(), displayArrayList, context)

        recyclerView = view.findViewById<RecyclerView>(R.id.recycler1)
        recyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        val jsonFileString = getJsonDataFromAsset(requireContext(), "cities")
        val gson = Gson()
        val listPersonType = object : TypeToken<List<Cities>>() {}.type
        var cities: List<Cities> = gson.fromJson(jsonFileString, listPersonType)
        //cities.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }

        itemArrayList.clear()
        itemArrayList.addAll(cities)
        displayArrayList.addAll(cities)
        itemArrayList.sortWith(Comparator { lhs, rhs ->
            if (lhs.cityName < rhs.cityName) -1 else 1
        })
        displayArrayList.sortWith(Comparator { lhs, rhs ->
            if (lhs.cityName < rhs.cityName) -1 else 1
        })

        recyclerView.adapter = adapter
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    fun onQueryTextChange(item: String) {

        Log.e("onQueryTextChange Fragment", "$item")

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


    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    override fun onClick(coords: Cities) {
       viewModel.selectedCity(coords)
    }

}