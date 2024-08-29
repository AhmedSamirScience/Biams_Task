package com.samir.baims.presentation.mainNav.fragment.weatherList.clickEvent

import com.samir.baims.domain.model.remote.main.Cities
import com.samir.baims.domain.model.remote.weatherList.WeatherList


interface WeatherClickListener {
    fun onItemClicked(itemSelected: WeatherList.WeatherSingleItem)
}