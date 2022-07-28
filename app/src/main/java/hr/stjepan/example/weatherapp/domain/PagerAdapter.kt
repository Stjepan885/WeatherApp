package hr.stjepan.example.weatherapp.domain

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.stjepan.example.weatherapp.presentaion.WeatherDailyFragment
import hr.stjepan.example.weatherapp.presentaion.WeatherHourlyFragment

class PagerAdapter
internal constructor(
    fragmentManager: FragmentManager?,
    private val numberOfTabs: Int,
    var context: Context
) :
    FragmentPagerAdapter(fragmentManager!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragments: MutableList<Fragment> = ArrayList()
    private val fragmentTitles: MutableList<String> = ArrayList()
    fun add(fragment: Fragment, title: String) {
        fragments.add(fragment)
        fragmentTitles.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> WeatherHourlyFragment()
            1 -> WeatherDailyFragment()
            else -> WeatherHourlyFragment()
        }
    }

    override fun getCount(): Int {
        return numberOfTabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Hourly"
            1 -> return "Daily"
        }
        return null
    }
}