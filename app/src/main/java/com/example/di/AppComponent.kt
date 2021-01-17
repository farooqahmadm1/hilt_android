package com.ibex.fleetmanager.driver.di

import android.app.Application
import com.ibex.fleetmanager.common.di.AppModule
import com.ibex.fleetmanager.common.di.DbModule
import com.ibex.fleetmanager.driver.DriverApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        DbModule::class,
        ActivityBuilderModule::class,
        ServiceBuilderModule::class,
        DriverModule::class,
        WorkerBindingModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: DriverApp)
}