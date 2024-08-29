package com.samir.baims.domain.mapper.weatherList

import com.samir.baims.data.remote.dto.weather.WeatherDtoRs
import com.samir.baims.domain.model.remote.weatherList.WeatherList

class WeatherRsMapper {
    fun buildFrom(response: WeatherDtoRs): WeatherList {

        return WeatherList(
            weatherItem = response.list!!.map {
                WeatherList.WeatherSingleItem(
                    humidity = it.main.humidity.toString() ,
                    temp = it.main.temp.toString(),
                    tempText =it.weather.get(0).main.toString(),
                    clouds = it.clouds.all.toString()
                )
            }
        )



    }
}

