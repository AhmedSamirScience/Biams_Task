package com.samir.baims.domain.useCase.weatherList

import android.util.Log
import com.samir.baims.R
import com.samir.baims.common.constants.LocalKeys
import com.samir.baims.common.stateHandling.useCase.RequestResource
import com.samir.baims.di.resourceProvider.ResourceProvider
import com.samir.baims.domain.model.remote.weatherList.WeatherList
import com.samir.baims.domain.repository.dataSource.LocalRepository
import com.samir.baims.domain.repository.remote.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class GetWeatherOfflineUseCase @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val localRepository: LocalRepository
) {
    operator fun invoke(countryName: String): Flow<RequestResource<WeatherList>> = flow {
        try {
            val response = localRepository.returnCountryWeatherList(LocalKeys.COUNTRY_WEATHER_LIST)
            if(response.countryName.equals(countryName)) {
                Log.e("asdasd", "invoke: ${response}")
                emit(RequestResource.Success(response))
            }
            else {
                Log.e("asdasd", "invoke elssseeee: ${response}")

                emit(RequestResource.Error(message = resourceProvider.getString(R.string.app_error_invalid_data_received)))
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
