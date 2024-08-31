package com.samir.baims.domain.repository.dataSource

import com.samir.baims.domain.model.remote.weatherList.WeatherList

interface LocalRepository {
    suspend fun saveCountryWeatherList(key: String, bodyRs: WeatherList)
    suspend fun returnCountryWeatherList(key: String): WeatherList
}