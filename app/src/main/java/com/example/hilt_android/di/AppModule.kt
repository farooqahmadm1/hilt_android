package com.example.hilt_android.di

import android.app.Application
import androidx.room.Room
import com.example.hilt_android.db.AppDatabase
import com.example.hilt_android.db.UserDao
import com.example.hilt_android.network.RestApiServices
import com.example.hilt_android.practice_network.call_adapter.LiveDataCallAdapterFactory
import com.example.hilt_android.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
class AppModule{

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): RestApiServices = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .client(client)
        .build()
        .create(RestApiServices::class.java)

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient{
        val httpInterceptor = HttpLoggingInterceptor()
        httpInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(httpInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                val request = requestBuilder.build()
                chain.proceed(request)
            }.build()
    }


    @Singleton
    @Provides
    fun provideRoomDatabase(app: Application): AppDatabase = Room
        .databaseBuilder(app, AppDatabase::class.java, "app_database")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Singleton
    @Provides
    fun provideSomthing(): String{
        return "It's some  String!"
    }
}