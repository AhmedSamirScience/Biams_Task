package com.samir.baims.data.remote.dto.weather

import com.google.gson.annotations.SerializedName

data class WeatherDtoRs(
   @SerializedName("cod")
   val cod: String,

   @SerializedName("message")
   val message: Long,

   @SerializedName("cnt")
   val cnt: Long,

    @SerializedName("list")
   val list: List<ListElement>,

   @SerializedName("city")
   val city: City
)
{
   data class City (
        @SerializedName("id")
      val id: Long,

     @SerializedName("name")
      val name: String,

     @SerializedName("coord")
      val coord: Coord,

     @SerializedName("country")
      val country: String,

     @SerializedName("population")
      val population: Long,

        @SerializedName("timezone")
      val timezone: Long,

        @SerializedName("sunrise")
      val sunrise: Long,

        @SerializedName("sunset")
      val sunset: Long
   )
   {
        data class Coord (
           @SerializedName("lat")
           val lat: Double,

           @SerializedName("lon")
           val lon: Double
        )

   }

   data class ListElement (

         @SerializedName("dt")
         val dt: Long,

         @SerializedName("main")
         val main: MainClass,

         @SerializedName("weather")
         val weather: List<Weather>,

         @SerializedName("clouds")
         val clouds: Clouds,

         @SerializedName("wind")
         val wind: Wind,

         @SerializedName("visibility")
         val visibility: Long,

         @SerializedName("pop")
         val pop: Long,

         @SerializedName("sys")
         val sys: Sys,

         @SerializedName("dt_txt")
         val dtTxt: String
   )
   {

      enum class Description(val value: String) {
         @SerializedName("broken clouds") BrokenClouds("broken clouds"),
         @SerializedName("clear sky") ClearSky("clear sky"),
         @SerializedName("few clouds") FewClouds("few clouds"),
         @SerializedName("scattered clouds") ScatteredClouds("scattered clouds");
      }

      data class Wind (
         @SerializedName("speed")
         val speed: Double,

         @SerializedName("deg")
         val deg: Long,

         @SerializedName("gust")
         val gust: Double
      )
      enum class MainEnum(val value: String) {
         @SerializedName("Clear") Clear("Clear"),
         @SerializedName("Clouds") Clouds("Clouds");
      }
      data class Weather (
            @SerializedName("id")
            val id: Long,

            @SerializedName("main")
            val main: MainEnum,

            @SerializedName("description")
            val description: Description,

            @SerializedName("icon")
            val icon: String
      )
      data class Sys (
         @SerializedName("pod")
         val pod: Pod
      )

      enum class Pod(val value: String) {
         @SerializedName("d") D("d"),
         @SerializedName("n") N("n");
      }
      data class Clouds (
         @SerializedName("all")
         val all: Long
      )

      data class MainClass (
         @SerializedName("temp")
         val temp: Double,

         @SerializedName("feels_like")
         val feelsLike: Double,

         @SerializedName("temp_min")
         val tempMin: Double,

         @SerializedName("temp_max")
         val tempMax: Double,

         @SerializedName("pressure")
         val pressure: Long,

         @SerializedName("sea_level")
         val seaLevel: Long,

         @SerializedName("grnd_level")
         val grndLevel: Long,

         @SerializedName("humidity")
         val humidity: Long,

         @SerializedName("temp_kf")
         val tempKf: Double
      )
   }

}





fun WeatherDtoRs.isDataValid(): Boolean {
   return !list.isNullOrEmpty() && list.all { listItem ->
       listItem.clouds.all != null && listItem.main.humidity != null && listItem.main.temp != null && !listItem.weather.isNullOrEmpty() && listItem.weather.get(0).main != null
   }
}






