package com.hoang.pagedlistexample.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest: KoinTest {
    val cityDao: CityDao by inject()

    @Test
    fun returnCorrectDatabaseSize() {
        val dbSize = cityDao.countCities()
        assertEquals("Database size should be 272128", dbSize, 272128)
    }
}