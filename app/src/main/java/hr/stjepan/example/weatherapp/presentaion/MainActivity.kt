package hr.stjepan.example.weatherapp.presentaion

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import hr.stjepan.example.weatherapp.R
import hr.stjepan.example.weatherapp.data.model.Day
import hr.stjepan.example.weatherapp.domain.PagerAdapter


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var currentWeatherFragment: CurrentWeatherFragment

    lateinit var pagerAdapter: PagerAdapter
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    lateinit var but: Button

    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currentWeatherFragment = CurrentWeatherFragment()

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


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.selectedItem.observe(this, Observer {
            setBackground(it.first, it.second)
        })

        but.setOnClickListener(View.OnClickListener {
            setBackground()
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
            3,4,9,10,11 -> {
                firstColor = R.color.cloudy_up
                secondColor = R.color.cloudy_down
            }

            13,50 -> {
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
                secondColor ="#5393b2"
            }

            else -> {
                i = -1
                firstColor = "#00ccff"
                secondColor = "#007acc"
            }
        }


        i++
        Log.e("i" , " $i ")


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
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}