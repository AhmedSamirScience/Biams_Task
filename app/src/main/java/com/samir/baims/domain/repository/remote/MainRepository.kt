package com.samir.baims.domain.repository.remote

import com.samir.baims.data.remote.dto.main.CitiesDtoRs

interface MainRepository {
    suspend fun getCities (): CitiesDtoRs

}