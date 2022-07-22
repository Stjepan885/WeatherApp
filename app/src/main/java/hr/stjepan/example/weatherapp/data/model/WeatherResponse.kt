package hr.stjepan.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("coord")
    val coordinates: Coord,
    @SerializedName("main")
    val temp: Main,
    @SerializedName("clouds")
    val clouds: Clouds
){
    override fun toString(): String {
        return "WeatherResponse(coordinates=$coordinates, temp=$temp, clouds=$clouds)"
    }
}

data class Coord(
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("lat")
    val lat: Double,
){
    override fun toString(): String {
        return "Coord(lon=$lon, lat=$lat)"
    }
}

data class Clouds(
    @SerializedName("all")
    val clouds: Double,
){
    override fun toString(): String {
        return "Clouds(clouds=$clouds)"
    }
}

data class Main(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("temp_min")
    val temp_min: Double,
    @SerializedName("temp_max")
    val temp_max: Double,
    @SerializedName("humidity")
    val humidity: Int,
){
    override fun toString(): String {
        return "Main(temp=$temp, temp_min=$temp_min, temp_max=$temp_max, humidity=$humidity)"
    }
}

