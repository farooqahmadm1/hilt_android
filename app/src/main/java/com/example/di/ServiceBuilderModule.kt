package com.ibex.fleetmanager.driver.di

import com.ibex.fleetmanager.driver.services.FCMBackgroundService
import com.ibex.fleetmanager.driver.services.LocationUpdatesService
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
internal abstract class ServiceBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeMyService(): LocationUpdatesService?

    @ContributesAndroidInjector
    abstract fun contributeFCMService(): FCMBackgroundService?
}