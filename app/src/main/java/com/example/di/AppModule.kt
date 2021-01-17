package com.ibex.fleetmanager.common.di


import android.app.Application
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.ibex.fleetmanager.common.BuildConfig
import com.ibex.fleetmanager.common.network.RestApiServices
import com.ibex.fleetmanager.common.prefrences.PreferenceManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): RestApiServices = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(RestApiServices::class.java)

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpInterceptor = HttpLoggingInterceptor()
        httpInterceptor.apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .protocols(Collections.singletonList(Protocol.HTTP_1_1))
            .addInterceptor(httpInterceptor)
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                val request = requestBuilder.build()
                chain.proceed(request)
            }.build()
    }


    @Singleton
    @Provides
    fun providePreferenceManager(app: Application): PreferenceManager = PreferenceManager(app)
}