package hr.stjepan.example.weatherapp.presentaion

import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import hr.stjepan.example.weatherapp.ConnectionLiveData
import hr.stjepan.example.weatherapp.R
import hr.stjepan.example.weatherapp.domain.PagerAdapter
import hr.stjepan.example.weatherapp.presentaion.viewModel.MainViewModel
import hr.stjepan.example.weatherapp.presentaion.viewModel.SearchViewModel
import hr.stjepan.example.weatherapp.presentaion.viewModel.WeatherViewModel

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var searchViewModel: SearchViewModel
    lateinit var weatherViewModel: WeatherViewModel

    lateinit var currentWeatherFragment: CurrentWeatherFragment
    lateinit var searchFragment: SearchFragment

    lateinit var pagerAdapter: PagerAdapter
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    lateinit var searchView: SearchView

    private lateinit var connectionLiveData: ConnectionLiveData

    private lateinit var text: TextView

    private var connection = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar!!.elevation = 0f

        val actionBar = supportActionBar

        weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        text = findViewById(R.id.noInternet)

        connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this) { isNetworkAvailable ->
            isNetworkAvailable?.let {
                if (!it) {
                    text.text = "No Internet Access"
                    connection = false
                } else {
                    text.text = ""
                    connection = true
                    if (weatherViewModel.weekWeather.value == null) {
                        weatherViewModel.setLocation(45.814442, 15.97798)
                        actionBar?.title = "Zagreb"
                    }
                }
            }
        }

        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        currentWeatherFragment = CurrentWeatherFragment()
        searchFragment = SearchFragment()

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.linearLayout, currentWeatherFragment)
            .commitNow()

        pagerAdapter = PagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        mainViewModel.selectedItem.observe(this) {
            setBackground(it.first, it.second)
        }

        searchViewModel.selectedCity.observe(this) {

            searchView.onActionViewCollapsed()
            supportActionBar?.title = it.cityName
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                .remove(searchFragment)
                .commitNow()

            if (connection) {
                weatherViewModel.setLocation(it.coords.lat, it.coords.lon)
            }
        }

        if (!isOnline(this)) {
            text.text = "No Internet Access"
            connection = false

        } else {
            weatherViewModel.setLocation(45.814442, 15.97798)
            actionBar?.title = "Zagreb"
            connection = true
        }


    }

    private fun setBackground(time: String, type: String) {
        val actionBar = supportActionBar
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        val firstColor: String
        val secondColor: String

        when (type.toInt()) {
            1, 2 -> if (time == "d") {
                firstColor = "#00ccff"
                secondColor = "#007acc"
            } else {
                firstColor = "#9561a1"
                secondColor = "#122259"

            }
            3, 4, 9, 10, 11 -> {
                firstColor = "#a6bdca"
                secondColor = "#5393b2"
            }

            13, 50 -> {
                firstColor = "#777b86"
                secondColor = "#5393b2"
            }

            else -> {
                firstColor = "#00ccff"
                secondColor = "#007acc"
            }
        }


        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(
                Color.parseColor(secondColor),
                Color.parseColor(firstColor)
            )
        )
        gradientDrawable.cornerRadius = 0f
        actionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(secondColor)))
        window.statusBarColor = Color.parseColor(secondColor)
        findViewById<View>(R.id.layoutMain).background = gradientDrawable

    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                ) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            ) {
                return true
            }
        }
        return false
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu.findItem(R.id.action_search)
        searchView = menuItem.actionView as SearchView

        searchView.setOnCloseListener {
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                .remove(searchFragment)
                .commitNow()
            false
        }

        searchView.setOnSearchClickListener {
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                .add(R.id.fragment_search, searchFragment)
                .commitNow()
            if (!connection) {
                searchView.onActionViewCollapsed()
                Toast.makeText(this@MainActivity, "No Internet Access", Toast.LENGTH_SHORT).show()
                supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(
                        R.anim.fade_in,
                        R.anim.fade_out
                    )
                    .remove(searchFragment)
                    .commitNow()
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.selectedItem("first")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchViewModel.selectedItem(newText.toString())
                return true
            }

        })
        return true
    }

    override fun onBackPressed() {
        searchView.onActionViewCollapsed()
        super.onBackPressed()
    }
}

