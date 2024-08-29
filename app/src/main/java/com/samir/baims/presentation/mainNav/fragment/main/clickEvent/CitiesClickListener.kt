package com.samir.baims.presentation.mainNav.fragment.main.clickEvent

import com.samir.baims.domain.model.remote.main.Cities


interface CitiesClickListener {
    fun onItemClicked(itemSelected: Cities.City)
}