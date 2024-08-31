package com.samir.baims.domain.model.remote.main

data class Cities(
   val cities: List<City>
)
{
   data class City(
      val cityNameAr: String,
      val cityNameEn: String,
      val lat: String,
      val lon: String
   )
}






