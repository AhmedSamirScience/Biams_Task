package com.samir.baims.domain.model.remote.weatherList



data class WeatherList(
   val weatherItem: List<WeatherSingleItem>,
   val countryName: String
)
{
   data class WeatherSingleItem(
      val humidity: String,
      val temp: String,
      val tempText: String,
      val clouds: String
   )
}








