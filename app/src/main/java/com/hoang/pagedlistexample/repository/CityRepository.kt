package com.hoang.pagedlistexample.repository

import androidx.paging.DataSource

class CityRepository(private val cityDao: CityDao) {
    fun getCities(): DataSource.Factory<Int, City> {
        return cityDao.loadCities()
    }
}