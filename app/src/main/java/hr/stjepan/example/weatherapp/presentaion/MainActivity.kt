package hr.stjepan.example.weatherapp.presentaion

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import hr.stjepan.example.weatherapp.R
import hr.stjepan.example.weatherapp.domain.PagerAdapter

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var searchViewModel: SearchViewModel
    lateinit var currentWeatherFragment: CurrentWeatherFragment
    lateinit var searchFragment: SearchFragment

    lateinit var pagerAdapter: PagerAdapter
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

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


/*
        tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager.currentItem = tab.position
                    Log.e("i" , " ${tab.position} ")
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {}
                override fun onTabReselected(tab: TabLayout.Tab) {}
            })

 */


        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.selectedItem.observe(this, Observer {
            setBackground(it.first, it.second)

        })

        searchViewModel.selectedCity.observe(this, Observer {
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
        })

    }

    private fun setBackground(time: String, type: String) {
        val actionBar = supportActionBar
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        var firstColor = ""
        var secondColor = ""

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
                Color.parseColor(firstColor),
                Color.parseColor(secondColor)
            )
        )
        gradientDrawable.cornerRadius = 0f
        actionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(firstColor)))
        window.statusBarColor = Color.parseColor(firstColor)
        findViewById<View>(R.id.layoutMain).background = gradientDrawable

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu.findItem(R.id.action_search)
        searchView = menuItem.actionView as SearchView

        searchView.setOnCloseListener(SearchView.OnCloseListener {

            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                .remove(searchFragment)
                .commitNow()

            false
        })

        searchView.setOnSearchClickListener {

            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                .add(R.id.fragment_search, searchFragment)
                .commitNow()
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
