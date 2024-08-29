package com.samir.baims.presentation.mainNav.fragment.weatherList


import android.util.Log
import androidx.lifecycle.viewModelScope
import com.samir.baims.common.base.BaseViewModel
import com.samir.baims.common.stateHandling.uI.LiveDataResource
import com.samir.baims.common.stateHandling.useCase.RequestResource
import com.samir.baims.domain.model.remote.weatherList.WeatherList
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
    private val getWeatherUseCase: GetWeatherUseCase
) : BaseViewModel() {
    override fun stop() {

    }

    override fun start() {
        fetchWeather()
    }

    private val _getCitiesStateFlow = MutableStateFlow<LiveDataResource<WeatherList>>(LiveDataResource.Loading())
    val getCitiesStateFlow: StateFlow<LiveDataResource<WeatherList>> = _getCitiesStateFlow
    fun fetchWeather() {
        viewModelScope.launch {
            getWeatherUseCase()
                .onStart {
                    Log.e("WeatherListViewModel", "fetchWeather: Loading")
                    _getCitiesStateFlow.value = LiveDataResource.Loading()
                }
                .map { resultResponse ->
                    when (resultResponse) {
                        is RequestResource.Success -> {
                            Log.e("WeatherListViewModel", "fetchWeather: ${resultResponse.data}")
                            LiveDataResource.Success(resultResponse.data)
                        }
                        is RequestResource.ErrorResponse -> {
                            Log.e("WeatherListViewModel", "fetchWeather: ${resultResponse.message}")
                            LiveDataResource.ErrorResponse(message = resultResponse.message.toString())
                        }
                        is RequestResource.Error -> {
                            Log.e("WeatherListViewModel", "fetchWeather: ${resultResponse.message}")
                            LiveDataResource.Error(message = resultResponse.message.toString())
                        }
                    }
                }
                .catch { exception ->
                    Log.e("WeatherListViewModel", "fetchWeather: ${exception.message}")
                    emit(LiveDataResource.Error(message = "An error occurred. Please try again."))
                }
                .collect { result ->
                    _getCitiesStateFlow.value = result
                }
        }
    }

}