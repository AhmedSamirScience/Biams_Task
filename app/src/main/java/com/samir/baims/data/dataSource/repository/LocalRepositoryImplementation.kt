package com.samir.baims.data.dataSource.repository

import com.samir.baims.data.dataSource.base.PreferencesManager
import com.samir.baims.domain.model.remote.weatherList.WeatherList
import com.samir.baims.domain.repository.dataSource.LocalRepository

import javax.inject.Inject

class LocalRepositoryImplementation @Inject constructor(
    private val prefManager: PreferencesManager
): LocalRepository
{
    override suspend fun saveCountryWeatherList(key: String, bodyRs: WeatherList) {
        prefManager.clearSharedPreferences()
        prefManager.saveObject(key,bodyRs )
    }
    override suspend fun returnCountryWeatherList(key: String): WeatherList {
        return prefManager.getObject(key , WeatherList::class.java)
    }
}