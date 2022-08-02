package hr.stjepan.example.weatherapp.presentaion

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
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

    lateinit var searchView:SearchView

    lateinit var but: Button

    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        currentWeatherFragment = CurrentWeatherFragment()
        searchFragment = SearchFragment()

        but = findViewById(R.id.button)
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

        searchViewModel.selectedItem.observe(this, Observer {
            Log.e("Search observer" , "$it ")
        })
        Log.e("Search observer fragment", "${searchViewModel.toString()} ")

        but.setOnClickListener(View.OnClickListener {
            //setBackground()
            if (searchFragment.isVisible) {
                supportFragmentManager.commit {
                    setCustomAnimations(
                        R.anim.fade_in,
                        R.anim.fade_out
                    )
                        .detach(searchFragment)
                }

            } else if (searchFragment.isDetached) {
                supportFragmentManager.commit {
                    setCustomAnimations(
                        R.anim.fade_in,
                        R.anim.fade_out
                    )
                        .attach(searchFragment)
                }

            } else if (!searchFragment.isAdded) {
                supportFragmentManager.commit {
                    setCustomAnimations(
                        R.anim.fade_in,
                        R.anim.fade_out
                    )
                    replace(R.id.fragment_search, searchFragment)
                    addToBackStack(null)
                }

            }
        })

    }

    private fun setBackground(time: String, type: String) {

        var firstColor = 0
        var secondColor = 0

        when (type.toInt()) {
            1, 2 -> if (time == "d") {
                firstColor = R.color.clear_up_day
                secondColor = R.color.clear_down_day
            } else {
                firstColor = R.color.clear_up_night
                secondColor = R.color.clear_down_night
            }
            3, 4, 9, 10, 11 -> {
                firstColor = R.color.cloudy_up
                secondColor = R.color.cloudy_down
            }

            13, 50 -> {
                firstColor = R.color.snow_up
                secondColor = R.color.snow_down
            }

            else -> {
                firstColor = R.color.clear_up_day
                secondColor = R.color.clear_down_day
            }
        }


        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(
                firstColor,
                firstColor,
                secondColor
            )
        )
        gradientDrawable.cornerRadius = 0f

        findViewById<View>(R.id.layoutMain).background = gradientDrawable
    }

    private fun setBackground() {

        viewPager.adapter = pagerAdapter

        var firstColor = ""
        var secondColor = ""


        when (i) {
            0 -> {
                firstColor = "#00ccff"
                secondColor = "#007acc"
            }
            1 -> {
                firstColor = "#9561a1"
                secondColor = "#122259"
            }
            2 -> {
                firstColor = "#a6bdca"
                secondColor = "#5393b2"
            }

            3 -> {
                firstColor = "#777b86"
                secondColor = "#5393b2"
            }

            else -> {
                i = -1
                firstColor = "#00ccff"
                secondColor = "#007acc"
            }
        }


        i++
        Log.e("i", " $i ")


        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(
                Color.parseColor(firstColor),
                Color.parseColor(secondColor)
            )
        )
        gradientDrawable.cornerRadius = 0f

        findViewById<View>(R.id.layoutMain).background = gradientDrawable
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        val menuItem = menu.findItem(R.id.action_search)
        Log.e("sdas", menuItem.toString())
        searchView = menuItem.actionView as SearchView


        searchView.setOnCloseListener(SearchView.OnCloseListener {

            Log.e("searchView" , "closed")
            supportFragmentManager.commit {
                setCustomAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                    .remove(searchFragment)

            }
            false
        })

        searchView.setOnSearchClickListener {
            Log.e("searchView" , "open")
            supportFragmentManager.commit {
                setCustomAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                replace(R.id.fragment_search, searchFragment)
                addToBackStack(null)
            }
        }


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.e("onQueryTextChange" , "$newText")
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
