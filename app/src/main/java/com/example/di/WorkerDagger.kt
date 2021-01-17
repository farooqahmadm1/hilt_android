package com.ibex.fleetmanager.driver.di

import androidx.work.ListenableWorker
import com.ibex.fleetmanager.driver.worker.ChildWorkerFactory
import com.ibex.fleetmanager.driver.worker.LocationUpdateWorker
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass


@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class WorkerKey(val value: KClass<out ListenableWorker>)

@Module
interface WorkerBindingModule {
    @Binds
    @IntoMap
    @WorkerKey(LocationUpdateWorker::class)
    fun bindHelloWorldWorker(factory: LocationUpdateWorker.Factory): ChildWorkerFactory
}