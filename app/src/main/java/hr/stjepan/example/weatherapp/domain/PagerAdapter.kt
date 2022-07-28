package hr.stjepan.example.weatherapp.domain

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.stjepan.example.weatherapp.presentaion.WeatherDailyFragment
import hr.stjepan.example.weatherapp.presentaion.WeatherHourlyFragment

class PagerAdapter(fm:FragmentManager):FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        Log.e("Stjepan", "$position")
        return when (position) {
            0 -> WeatherDailyFragment()
            1 -> WeatherDailyFragment()
            else -> WeatherDailyFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Hourl"
            1 -> return "Daily"
        }
        return super.getPageTitle(position)
    }
}