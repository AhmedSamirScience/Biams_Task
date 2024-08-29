package com.samir.baims.domain.useCase.main

import com.samir.baims.R
import com.samir.baims.common.stateHandling.useCase.RequestResource
import com.samir.baims.data.remote.dto.main.isDataValid
import com.samir.baims.di.resourceProvider.ResourceProvider
import com.samir.baims.domain.mapper.main.CitiesRsMapper
import com.samir.baims.domain.model.remote.main.Cities
import com.samir.baims.domain.repository.remote.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: MainRepository,
    private val resourceProvider: ResourceProvider
) {
    operator fun invoke(): Flow<RequestResource<Cities>> = flow {
        try {
                val getServerTimeResponseDTO = repository.getCities()

                val response = withContext(Dispatchers.IO) {
                    CitiesRsMapper().buildFrom(response = getServerTimeResponseDTO)
                }

                if (!getServerTimeResponseDTO.isDataValid()) {
                    emit(RequestResource.Error(message = resourceProvider.getString(R.string.app_error_invalid_data_received)))
                } else {
                    emit(RequestResource.Success(Cities(cities = response.cities )))
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
