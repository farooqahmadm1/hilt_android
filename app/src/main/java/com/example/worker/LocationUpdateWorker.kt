package com.ibex.fleetmanager.driver.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.ibex.fleetmanager.common.base.Success
import com.ibex.fleetmanager.common.network.driver.requestBody.DriverLocationRequest
import com.ibex.fleetmanager.common.network.driver.responses.TripUpdate
import com.ibex.fleetmanager.common.prefrences.PreferenceManager
import com.ibex.fleetmanager.common.utility.TripEnums
import com.ibex.fleetmanager.driver.ui.home.data.HomeRepo
import kotlinx.coroutines.flow.collect
import okhttp3.internal.wait
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider


class LocationUpdateWorker(
    private val context: Context,
    private val workParams: WorkerParameters,
    private val repo: HomeRepo,
    private val preferenceManager: PreferenceManager
) : CoroutineWorker(context, workParams) {
    override suspend fun doWork(): Result {
        Timber.d("$TAG: LocationUpdateWorkerCalled")
        repo.getTripOfflineLocalLocations().collect { listLocalLocation ->
            listLocalLocation.forEach { localLocation ->
                val request = DriverLocationRequest(
                    localLocation.time,
                    preferenceManager.getString(TripEnums.CURRENT_TRIP_ID.key).toString(),
                    localLocation.latitude,
                    localLocation.longitude
                )
                repo.updateLocation(Gson().toJson(request))
                    .collect { result ->
                        when (result) {
                            is Success<TripUpdate> -> {
                                localLocation.online = 1
                                repo.updateTripLocalLocation(localLocation)
                            }
                            else -> ""
                        }
                    }
            }
        }.wait()
        return Result.success()
    }

    class Factory @Inject constructor(
        private val repo: Provider<HomeRepo>,
        private val preference: Provider<PreferenceManager>
    ) : ChildWorkerFactory {
        override fun create(appContext: Context, params: WorkerParameters): ListenableWorker {
            return LocationUpdateWorker(
                appContext,
                params,
                repo.get(),
                preference.get()
            )
        }
    }

    companion object {
        private const val TAG = "LocationUpdateWorker"
    }
}