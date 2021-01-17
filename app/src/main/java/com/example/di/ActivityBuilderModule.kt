package com.ibex.fleetmanager.driver.di

import com.ibex.fleetmanager.driver.SplashActivity
import com.ibex.fleetmanager.driver.ui.AuthActivity
import com.ibex.fleetmanager.driver.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(includes = [ViewModelModule::class])
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [FragmentAuthBuildersModule::class])
    abstract fun contributeAuthActivity(): AuthActivity

    @ContributesAndroidInjector(modules = [FragmentMainBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector()
    abstract fun contributeSplashActivity(): SplashActivity
}