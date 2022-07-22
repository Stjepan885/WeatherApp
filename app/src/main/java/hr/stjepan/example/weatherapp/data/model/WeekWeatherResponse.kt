package hr.stjepan.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class WeekWeatherResponse(
    @SerializedName("city")
    val cities: Cities,
    @SerializedName("list")
    var list: ArrayList<Day>,

    ){
}

data class Cities(
    @SerializedName("name")
    val cityName: String,
    @SerializedName("coord")
    val coords: CoordCity
){
    override fun toString(): String {
        return "Cities(cityName='$cityName', coords=$coords)"
    }
}

data class CoordCity(
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("lat")
    val lat: Double,
){
    override fun toString(): String {
        return "Coord(lon=$lon, lat=$lat)"
    }
}

data class Day(
    @SerializedName("temp")
    val temp: Tempera,
    @SerializedName("weather")
    val dayWeather: ArrayList<DayWeather>
){
    override fun toString(): String {
        return "Day(temp='$temp', dayWeather=$dayWeather)"
    }
}

data class Tempera(
    @SerializedName("day")
    val day: Double,
    @SerializedName("min")
    val temp_min: Double,
    @SerializedName("max")
    val temp_max: Double,
){
    override fun toString(): String {
        return "DayTemps(temp=$day, temp_min=$temp_min, temp_max=$temp_max)"
    }
}

data class DayWeather(
    @SerializedName("main")
    val type: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
)

/*
package hr.stjepan.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class WeekWeatherResponse(
    @SerializedName("city")
    val cities: Cities,
    @SerializedName("list")
    var tempList: ArrayList<Day>,

    ){
}

data class Cities(
    @SerializedName("name")
    val cityName: String,
    @SerializedName("coord")
    val coords: CoordCity
){
    override fun toString(): String {
        return "Cities(cityName='$cityName', coords=$coords)"
    }
}

data class CoordCity(
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("lat")
    val lat: Double,
){
    override fun toString(): String {
        return "Coord(lon=$lon, lat=$lat)"
    }
}

data class Day(
    @SerializedName("temp")
    val dayTemps: DayTemps,
    @SerializedName("weather")
    val dayWeather: ArrayList<DayWeather>,
){
    override fun toString(): String {
        return "Day(dayTemps=$dayTemps, dayWeather=$dayWeather)"
    }
}

data class DayTemps(
    @SerializedName("day")
    val day: Double,
    @SerializedName("min")
    val temp_min: Double,
    @SerializedName("max")
    val temp_max: Double,
){
    override fun toString(): String {
        return "DayTemps(temp=$day, temp_min=$temp_min, temp_max=$temp_max)"
    }
}

data class DayWeather(
    @SerializedName("main")
    val type: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
)
 */