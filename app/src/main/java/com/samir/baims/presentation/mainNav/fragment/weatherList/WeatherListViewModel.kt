package com.samir.baims.presentation.mainNav.fragment.weatherList


import android.util.Log
import androidx.lifecycle.viewModelScope
import com.samir.baims.common.base.BaseViewModel
import com.samir.baims.common.stateHandling.uI.LiveDataResource
import com.samir.baims.common.stateHandling.useCase.RequestResource
import com.samir.baims.domain.model.remote.weatherList.WeatherList
import com.samir.baims.domain.useCase.weatherList.GetWeatherOfflineUseCase
import com.samir.baims.domain.useCase.weatherList.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getWeatherOfflineUseCase: GetWeatherOfflineUseCase
) : BaseViewModel() {
    override fun stop() {

    }

    override fun start() {
     }

    private val _getWeatherStateFlow = MutableStateFlow<LiveDataResource<WeatherList>>(LiveDataResource.Loading())
    val getWeatherStateFlow: StateFlow<LiveDataResource<WeatherList>> = _getWeatherStateFlow
    fun fetchWeather(lat: String, lon: String, appId: String, countryName: String) {
        viewModelScope.launch {
            getWeatherUseCase(lat= lat, lon=lon, appId=appId, countryName=countryName)
                .onStart {
                    _getWeatherStateFlow.value = LiveDataResource.Loading()
                }
                .map { resultResponse ->
                    when (resultResponse) {
                        is RequestResource.Success -> LiveDataResource.Success(resultResponse.data)
                        is RequestResource.ErrorResponse -> LiveDataResource.ErrorResponse(message = resultResponse.message.toString())
                        is RequestResource.Error -> LiveDataResource.Error(message = resultResponse.message.toString())
                    }
                }
                .catch { exception ->
                    emit(LiveDataResource.Error(message = "An error occurred. Please try again."))
                }
                .collect { result ->
                    _getWeatherStateFlow.value = result
                }
        }
    }


    private val _getWeatherOfflineStateFlow = MutableStateFlow<LiveDataResource<WeatherList>>(LiveDataResource.Loading())
    val getWeatherOfflineStateFlow: StateFlow<LiveDataResource<WeatherList>> = _getWeatherOfflineStateFlow
    fun fetchWeatherOffline(countryName: String) {
        viewModelScope.launch {
            getWeatherOfflineUseCase(countryName)
                .onStart {
                    _getWeatherOfflineStateFlow.value = LiveDataResource.Loading()
                }
                .map { resultResponse ->
                    when (resultResponse) {
                        is RequestResource.Success -> LiveDataResource.Success(resultResponse.data)
                        is RequestResource.ErrorResponse -> LiveDataResource.ErrorResponse(message = resultResponse.message.toString())
                        is RequestResource.Error -> LiveDataResource.Error(message = resultResponse.message.toString())
                    }
                }
                .catch { exception ->
                    emit(LiveDataResource.Error(message = "An error occurred. Please try again."))
                }
                .collect { result ->
                    _getWeatherOfflineStateFlow.value = result
                }
        }
    }

}