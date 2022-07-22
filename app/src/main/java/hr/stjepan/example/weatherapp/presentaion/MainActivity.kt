package hr.stjepan.example.weatherapp.presentaion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hr.stjepan.example.weatherapp.BuildConfig
import hr.stjepan.example.weatherapp.R

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.weather.observe(this, Observer {
            println("DEBUG: $it")
        })


        viewModel.weekWeather.observe(this, Observer {
            println("DEBUG2: ${it}")
            println("DEBUG3: ${it.cities}")
            println("DEBUG4: ${it.list}")
            println("DEBUG5: ${it.list[0]}")
            println("DEBUG6: ${it.list[0].dayWeather}")
            println("DEBUG2: ${it.list}")
        })

        //viewModel.setLocation(50.0,16.0)
        viewModel.setWeekLocation(35.0,139.0)
    }
}