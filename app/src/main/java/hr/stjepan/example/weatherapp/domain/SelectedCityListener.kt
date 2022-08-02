package hr.stjepan.example.weatherapp.domain

import hr.stjepan.example.weatherapp.data.model.Cities
import hr.stjepan.example.weatherapp.data.model.CoordCity

interface SelectedCityListener {
    fun onClick(coords: Cities)
}