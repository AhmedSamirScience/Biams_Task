package com.samir.baims.domain.repository.remote

import com.samir.baims.data.remote.dto.main.CitiesDtoRs
import com.samir.baims.data.remote.dto.weather.WeatherDtoRs

interface MainRepository {
    suspend fun getCities (): CitiesDtoRs
    suspend fun getWeather(  lat: String, lon: String,  appid: String): WeatherDtoRs

}