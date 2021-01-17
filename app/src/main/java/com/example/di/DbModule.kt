package com.ibex.fleetmanager.common.di

import android.app.Application
import androidx.room.Room
import com.ibex.fleetmanager.common.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
    @Singleton
    @Provides
    fun provideRoomDatabase(app: Application): AppDatabase = Room
        .databaseBuilder(app, AppDatabase::class.java, "fleet_manager_db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideWeatherDao(appDatabase: AppDatabase) = appDatabase.profileDao()

    @Provides
    @Singleton
    fun provideDriverUpcomingTrip(db: AppDatabase) = db.scheduleDao()

    @Provides
    @Singleton
    fun provideUserUpcomingTrip(db: AppDatabase) = db.userScheduleDao()

    @Provides
    @Singleton
    fun provideDriverTrip(db: AppDatabase) = db.tripDao()

    @Provides
    @Singleton
    fun provideLocationDao(db: AppDatabase) = db.locationDao()

    @Provides
    @Singleton
    fun provideSettingsDao(db: AppDatabase) = db.settingsDao()
}