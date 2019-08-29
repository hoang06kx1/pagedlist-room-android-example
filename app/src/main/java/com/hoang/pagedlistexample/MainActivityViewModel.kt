package com.hoang.pagedlistexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hoang.pagedlistexample.repository.City
import com.hoang.pagedlistexample.repository.CityRepository

class MainActivityViewModel(repository: CityRepository): ViewModel() {
    val cityList: LiveData<PagedList<City>> = LivePagedListBuilder(repository.getCities(), 50).build()
}