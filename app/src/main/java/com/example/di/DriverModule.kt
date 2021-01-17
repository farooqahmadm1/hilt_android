package com.ibex.fleetmanager.driver.di

import com.ibex.fleetmanager.common.db.common.LocationDao
import com.ibex.fleetmanager.common.db.driver.TripDao
import com.ibex.fleetmanager.common.network.RestApiServices
import com.ibex.fleetmanager.common.prefrences.PreferenceManager
import com.ibex.fleetmanager.driver.ui.home.data.HomeRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DriverModule {
    @Singleton
    @Provides
    fun provideHomeRepo(
        preferenceManager: PreferenceManager,
        restApiServices: RestApiServices,
        tripDao: TripDao,
        locationDao: LocationDao
    ): HomeRepo {
        return HomeRepo(restApiServices, preferenceManager, tripDao, locationDao)
    }
}