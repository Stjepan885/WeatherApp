package hr.stjepan.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class HourWeatherModel(
    @SerializedName("city")
    val city: City,
    @SerializedName("list")
    var listHour: ArrayList<Hour>,

    ) {
}

data class City(
    @SerializedName("name")
    val cityName: String,
    @SerializedName("coord")
    val coords: CoordCityHour,
    @SerializedName("id")
    val idCity: Int
) {

}

data class CoordCityHour(
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("lat")
    val lat: Double,
) {
    override fun toString(): String {
        return "Coord(lon=$lon, lat=$lat)"
    }
}

data class Hour(
    @SerializedName("main")
    val temps: Temperature,
    @SerializedName("weather")
    val dayWeather: ArrayList<DayWeather>,
    @SerializedName("dt")
    val day: Long
) {

}

data class Temperature(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("temp_min")
    val minTemp: Double,
    @SerializedName("temp_max")
    val maxTemp: Double,
    @SerializedName("humidity")
    val humidity: Int
)



