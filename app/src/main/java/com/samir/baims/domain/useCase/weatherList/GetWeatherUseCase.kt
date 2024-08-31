package com.samir.baims.domain.useCase.weatherList

import android.util.Log
import com.samir.baims.R
import com.samir.baims.common.constants.LocalKeys
import com.samir.baims.common.stateHandling.useCase.RequestResource
import com.samir.baims.data.remote.dto.weather.isDataValid
import com.samir.baims.di.resourceProvider.ResourceProvider
import com.samir.baims.domain.mapper.weatherList.WeatherRsMapper
import com.samir.baims.domain.model.remote.weatherList.WeatherList
import com.samir.baims.domain.repository.dataSource.LocalRepository
import com.samir.baims.domain.repository.remote.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: MainRepository,
    private val resourceProvider: ResourceProvider,
    private val localRepository: LocalRepository
) {
    operator fun invoke(lat: String, lon: String, appId: String, countryName:String  ): Flow<RequestResource<WeatherList>> = flow {
        try {
                val getWeatherResponseDTO = repository.getWeather(lat = lat, lon = lon, appid = appId)

                val response = withContext(Dispatchers.IO) {
                    WeatherRsMapper().buildFrom(response = getWeatherResponseDTO)
                }

                if (!getWeatherResponseDTO.isDataValid()) {
                    emit(RequestResource.Error(message = resourceProvider.getString(R.string.app_error_invalid_data_received)))
                } else {
                    localRepository.saveCountryWeatherList(LocalKeys.COUNTRY_WEATHER_LIST,WeatherList(weatherItem = response.weatherItem, countryName = countryName))
                    emit(RequestResource.Success( WeatherList(weatherItem = response.weatherItem, countryName = countryName) ))
                }
        } catch (e: HttpException) {
           emit(RequestResource.Error(message = resourceProvider.getString(R.string.app_error_http_exception)))
        } catch (e: SocketTimeoutException) {
           emit(RequestResource.Error(message = resourceProvider.getString(R.string.app_error_timeout_exception)))
        } catch (e: IOException) {
            emit(RequestResource.Error(message = resourceProvider.getString(R.string.app_error_io_exception)))
        } catch (e: Exception) {
            emit(RequestResource.Error(message = resourceProvider.getString(R.string.app_error_unexpected_exception)))
        }
    }
}
