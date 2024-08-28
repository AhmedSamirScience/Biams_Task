package com.samir.baims.data.repository

import com.samir.baims.domain.repository.remote.MainRepository
import com.samir.baims.data.remote.apiService.MainApi

import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: MainApi
) : MainRepository {


}