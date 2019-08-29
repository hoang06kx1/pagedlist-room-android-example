package com.hoang.pagedlistexample.di

import android.content.Context
import com.hoang.pagedlistexample.MainActivityViewModel
import com.hoang.pagedlistexample.repository.AppDatabase
import com.hoang.pagedlistexample.repository.CityDao
import com.hoang.pagedlistexample.repository.CityRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val sharableModule = module {
    single { providesCityDao(androidContext()) }
    single { providesRepository(get()) }
    viewModel { MainActivityViewModel(repository = get()) }
}

private fun providesCityDao(context: Context): CityDao {
    return AppDatabase.getInstance(context).CityDao()
}

private fun providesRepository(cityDao: CityDao): CityRepository {
    return CityRepository(cityDao)
}
