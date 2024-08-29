package com.samir.baims.data.repository

import com.samir.baims.domain.repository.remote.MainRepository
import com.samir.baims.data.remote.apiService.MainApi
import com.samir.baims.data.remote.dto.main.CitiesDtoRs

import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: MainApi
) : MainRepository {
    override suspend fun getCities(): CitiesDtoRs {
        return api.getCities( )
    }
}