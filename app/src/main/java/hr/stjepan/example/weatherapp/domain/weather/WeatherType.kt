package hr.stjepan.example.weatherapp.domain.weather

sealed class WeatherType(
    val weatherDesc: String,
    val iconResUrl: String
) {
    object ClearSky : WeatherType(
        weatherDesc = "Clear sky",
        iconResUrl = "/img/wn/01d@2x.png"
    )

    object FewClouds : WeatherType(
        weatherDesc = "few clouds",
        iconResUrl = "/img/wn/02d@2x.png"
    )

    object ScatteredClouds : WeatherType(
        weatherDesc = "Scattered clouds",
        iconResUrl = "/img/wn/03d@2x.png"
    )

    object BrokenClouds : WeatherType(
        weatherDesc = "Broken clouds",
        iconResUrl = "/img/wn/04d@2x.png"
    )

    object ShowerRain : WeatherType(
        weatherDesc = "Shower rain",
        iconResUrl = "/img/wn/09d@2x.png"
    )

    object Rain : WeatherType(
        weatherDesc = "Rain",
        iconResUrl = "/img/wn/10d@2x.png"
    )

    object Thunderstorm : WeatherType(
        weatherDesc = "Thunderstorm",
        iconResUrl = "/img/wn/11d@2x.png"
    )

    object Snow : WeatherType(
        weatherDesc = "Snow",
        iconResUrl = "/img/wn/13d@2x.png"
    )

    object Mist : WeatherType(
        weatherDesc = "Mist",
        iconResUrl = "/img/wn/50d@2x.png"
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when(code){
                800 -> ClearSky
                801 -> FewClouds
                802 -> ScatteredClouds
                803 -> BrokenClouds
                804 -> BrokenClouds
                701 -> Mist
                601 -> Snow
                501 -> Rain
                301 -> ShowerRain
                211 -> Thunderstorm
                else -> ClearSky
            }
        }
    }
}
