package com.samir.baims.data.remote.dto.main

import com.google.gson.annotations.SerializedName

data class CitiesDtoRs(
   @SerializedName("cities")
   val cities: List<City>? = null
)
{
   data class City(
      @SerializedName("id")
      val id: Long? = null,

      @SerializedName("cityNameAr")
      val cityNameAr: String? = null,

      @SerializedName("cityNameEn")
      val cityNameEn: String? = null,

      @SerializedName("lat")
      val lat: Double? = null,

      @SerializedName("lon")
      val lon: Double? = null
   )
}

fun CitiesDtoRs.isDataValid(): Boolean {
   return !cities.isNullOrEmpty() && cities.all { city ->
      !city.cityNameEn.isNullOrEmpty() && !city.cityNameAr.isNullOrEmpty() && city.lon != null && city.lat != null && city.id != null
   }
}






