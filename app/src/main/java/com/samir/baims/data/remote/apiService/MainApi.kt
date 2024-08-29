package com.samir.baims.data.remote.apiService

import com.samir.baims.common.constants.Constants
import com.samir.baims.data.remote.dto.main.CitiesDtoRs
import retrofit2.http.GET

interface MainApi {

    //region Main Fragment
    @GET(Constants.BASE_URL_EXTERNAL)
    suspend fun getCities( ): CitiesDtoRs
    //endregion

}