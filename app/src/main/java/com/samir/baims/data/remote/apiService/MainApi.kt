package com.samir.baims.data.remote.apiService

import com.samir.baims.common.constants.Constants
import com.samir.baims.data.remote.dto.main.CitiesDtoRs
import com.samir.baims.data.remote.dto.weather.WeatherDtoRs
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    //region Main Fragment
    @GET(Constants.BASE_URL_EXTERNAL)
    suspend fun getCities( ): CitiesDtoRs

    @GET("forecast")
    suspend fun  getWeather(  @Query("lat") lat: String,@Query("lon") lon: String,@Query("appid") appid: String  ): WeatherDtoRs
    //endregion



}