package com.samir.baims.presentation.mainNav.fragment.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.samir.baims.common.base.BaseViewModel
import com.samir.baims.common.stateHandling.uI.LiveDataResource
import com.samir.baims.common.stateHandling.useCase.RequestResource
import com.samir.baims.domain.model.remote.main.Cities
import com.samir.baims.domain.useCase.main.GetCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
) : BaseViewModel() {
    override fun stop() {}
    override fun start() {}

    private val _getCitiesStateFlow = MutableStateFlow<LiveDataResource<Cities>>(LiveDataResource.Loading())
    val getCitiesStateFlow: StateFlow<LiveDataResource<Cities>> = _getCitiesStateFlow
    fun fetchCities() {
        viewModelScope.launch {
            getCitiesUseCase()
                .onStart {
                    _getCitiesStateFlow.value = LiveDataResource.Loading()
                }
                .map { resultResponse ->
                    when (resultResponse) {
                        is RequestResource.Success -> {
                            LiveDataResource.Success(resultResponse.data)
                        }
                        is RequestResource.ErrorResponse -> {
                            LiveDataResource.ErrorResponse(message = resultResponse.message.toString())
                        }
                        is RequestResource.Error -> {
                            LiveDataResource.Error(message = resultResponse.message.toString())
                        }
                    }
                }
                .catch { exception ->
                    emit(LiveDataResource.Error(message = "An error occurred. Please try again."))
                }
                .collect { result ->
                    _getCitiesStateFlow.value = result
                }
        }
    }
}

