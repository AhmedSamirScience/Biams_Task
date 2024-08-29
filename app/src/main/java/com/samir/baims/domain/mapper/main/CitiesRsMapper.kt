package com.samir.baims.domain.mapper.main

import com.samir.baims.data.remote.dto.main.CitiesDtoRs
import com.samir.baims.domain.model.remote.main.Cities


class CitiesRsMapper {
    fun buildFrom(response: CitiesDtoRs): Cities {
        return Cities(
            cities = response.cities!!.map {
                Cities.City(
                    cityNameAr = it.cityNameAr ?: "",
                    cityNameEn = it.cityNameEn ?: ""
                )
            }
        )

    }
}

