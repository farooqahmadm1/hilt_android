package com.ibex.fleetmanager.common.network

import androidx.annotation.WorkerThread
import com.ibex.fleetmanager.common.BuildConfig
import com.ibex.fleetmanager.common.network.common.response.ChatNotificationResponse
import com.ibex.fleetmanager.common.network.driver.responses.*
import com.ibex.fleetmanager.common.network.responses.AddScheduleResponse
import com.ibex.fleetmanager.common.network.responses.OfficeBuildingResponse
import com.ibex.fleetmanager.common.network.responses.RoutesResponse
import com.ibex.fleetmanager.common.network.responses.TripsResponse
import com.ibex.fleetmanager.common.network.user.responses.*
import com.ibex.fleetmanager.common.network.user.responses.ProfileResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface RestApiServices {

    @WorkerThread
    @POST("TokenAuth/Authenticate")
    suspend fun authenticateUser(@Body requestBody: RequestBody): Response<AuthenticateResponse>

    @WorkerThread
    @POST("${BuildConfig.SERVICE_ENDPOINT}Account/Register")
    suspend fun registerUser(@Body requestBody: RequestBody): Response<RegisterResponse>

    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}Passenger/Get")
    suspend fun getProfile(
        @Header("Authorization") authorizationToken: String, @Query("Id") id: Int
    ): Response<ProfileResponse>

    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}Setting/GetSettingsForMobile")
    suspend fun getSettings(
        @Header("Authorization") authorizationToken: String
    ): Response<SettingsResponse>

    @WorkerThread
    @PUT("${BuildConfig.SERVICE_ENDPOINT}Passenger/Update")
    suspend fun editProfile(
        @Header("Authorization") authorizationToken: String,
        @Body requestBody: RequestBody
    ): Response<ProfileResponse>

    @WorkerThread
    @POST("${BuildConfig.SERVICE_ENDPOINT}User/ChangePassword")
    suspend fun changePassword(
        @Header("Authorization") authorizationToken: String,
        @Body requestBody: RequestBody
    ): Response<ChangePasswordResponse>

    @WorkerThread
    @POST("${BuildConfig.SERVICE_ENDPOINT}Account/ResetForgotPassword")
    suspend fun resetForgotPassword(@Body requestBody: RequestBody): Response<ResetForgotPasswordResponse>

    @WorkerThread
    @POST("${BuildConfig.SERVICE_ENDPOINT}Account/ForgotPassword")
    suspend fun resetPasswordEmail(@Query("emailAddress") email: String): Response<ResetPasswordEmailResponse>

    @WorkerThread
    @Multipart
    @POST("Media/SaveProfilePicture")
    suspend fun uploadFile(
        @Header("Authorization") authorizationToken: String,
        @Part("UserId") userId: RequestBody,
        @Part("MediaType") mediaType: RequestBody,
        @Part file: MultipartBody.Part
    ): Response<MediaUploadResponse>

    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}OfficeBuilding/GetAll")
    suspend fun getOfficeBuildingAll(): Response<OfficeBuildingResponse>

    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}Route/GetRouteInRadius")
    suspend fun getRoutesForCityAll(
        @Query("City") cityNameParam: String,
        @Query("Lat") lat: Double,
        @Query("Long") long: Double
    ): Response<RoutesResponse>

    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}Trip/GetAllAvailableTrips")
    suspend fun getTripsForRoutesAll(
        @Query("RouteId") routeId: Int,
        @Query("MaxResultCount") maxResultCount: Int
    ): Response<TripsResponse>

    @WorkerThread
    @POST("${BuildConfig.SERVICE_ENDPOINT}Schedule/Create")
    suspend fun uploadSchedule(
        @Header("Authorization") authorizationToken: String,
        @Body requestBody: RequestBody
    ): Response<AddScheduleResponse>

    @WorkerThread
    @PUT("${BuildConfig.SERVICE_ENDPOINT}Schedule/Update")
    suspend fun updateSchedule(
        @Header("Authorization") authorizationToken: String,
        @Body requestBody: RequestBody
    ): Response<AddScheduleResponse>

    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}Schedule/GetUpcomingSchedules")
    suspend fun getUpcomingSchdule(
        @Header("Authorization") authorizationToken: String,
        @Query("UserId") userId: Int,
        @Query("SkipCount") skipCount: Int,
        @Query("MaxResultCount") maxResultCount: Int
    ): Response<UpcomingSchduleResponse>

    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}Schedule/Get")
    suspend fun getScheduleDetailsForSingle(
        @Header("Authorization") authorizationToken: String,
        @Query("Id") scheduleID: Int
    ): Response<ScheduleDetailsResponse>

    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}Schedule/GetAll")
    suspend fun getAllSchdules(
        @Header("Authorization") authorizationToken: String,
        @Query("PastTrips") pastTrips: Boolean,
        @Query("FutureTrips") futureTrips: Boolean,
        @Query("UserId") userId: Int,
        @Query("Sorting") sorting: String,
        @Query("SkipCount") skipCount: Int,
        @Query("MaxResultCount") maxResultCount: Int
    ): Response<SchedulesResponse>

    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}TripExecution/GetTripsHistoryForPassenger")
    suspend fun getPastSchdule(
        @Header("Authorization") authorizationToken: String,
        @Query("PassengerId") userId: Int,
        @Query("SkipCount") skipCount: Int,
        @Query("MaxResultCount") maxResultCount: Int
    ): Response<PastSchduleResponse>

    //Driver Profile
    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}Driver/Get")
    suspend fun getDriverProfile(
        @Header("Authorization") authorizationToken: String, @Query("Id") id: Int
    ): Response<com.ibex.fleetmanager.common.network.driver.responses.ProfileResponse>


    @WorkerThread
    @PUT("${BuildConfig.SERVICE_ENDPOINT}Driver/UpdateProfile")
    suspend fun updateDriverProfile(
        @Header("Authorization") authorizationToken: String,
        @Body requestBody: RequestBody
    ): Response<com.ibex.fleetmanager.common.network.driver.responses.ProfileResponse>

    @WorkerThread
    @PUT("${BuildConfig.SERVICE_ENDPOINT}Driver/UpdateVehicle")
    suspend fun updateVehicle(
        @Header("Authorization") authorizationToken: String,
        @Body requestBody: RequestBody
    ): Response<VehicleUpdateResponse>


    @WorkerThread
    @Multipart
    @POST("Media/SaveProfilePicture")
    suspend fun uploadDriverFile(
        @Header("Authorization") authorizationToken: String,
        @Part("DriverId") userId: RequestBody,
        @Part("MediaType") mediaType: RequestBody,
        @Part file: MultipartBody.Part
    ): Response<MediaUploadResponse>

    @WorkerThread
    @Multipart
    @POST("Media/SaveProfilePicture")
    suspend fun uploadVehicleFile(
        @Header("Authorization") authorizationToken: String,
        @Part("VehicleId") userId: RequestBody,
        @Part("MediaType") mediaType: RequestBody,
        @Part file: MultipartBody.Part
    ): Response<MediaUploadResponse>


    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}Driver/GetDriverUpcomingTrips")
    suspend fun getDriverUpcomingTrips(
        @Header("Authorization") authorizationToken: String, @Query("driverId") id: Int
    ): Response<UpcomingResponse>

    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}TripExecution/GetTripsHistoryForDriver")
    suspend fun getPastTrips(
        @Header("Authorization") authorizationToken: String,
        @Query("driverId") id: Int
    ): Response<PastTripResponse>

    @WorkerThread
    @POST("${BuildConfig.SERVICE_ENDPOINT}TripExecution/Create")
    suspend fun startTrip(
        @Header("Authorization") authorizationToken: String,
        @Body requestBody: RequestBody
    ): Response<TripStartResponse>

    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}TripExecution/Get")
    suspend fun getTrip(
        @Header("Authorization") authorizationToken: String,
        @Query("Id") id: String
    ): Response<TripStartResponse>


    @WorkerThread
    @PUT("${BuildConfig.SERVICE_ENDPOINT}TripExecution/UpdateTripExecution")
    suspend fun updateTrip(
        @Header("Authorization") authorizationToken: String,
        @Body requestBody: RequestBody
    ): Response<TripUpdateResponse>

    @WorkerThread
    @PUT("${BuildConfig.SERVICE_ENDPOINT}TripExecution/UpdatePassengerBoardingStatus")
    suspend fun updateBoarding(
        @Header("Authorization") authorizationToken: String,
        @Body requestBody: RequestBody
    ): Response<TripUpdateResponse>

    @WorkerThread
    @POST("${BuildConfig.SERVICE_ENDPOINT}TripExecution/CreateDriverLocation")
    suspend fun updateLocation(
        @Header("Authorization") authorizationToken: String,
        @Body requestBody: RequestBody
    ): Response<TripUpdateResponse>


    @WorkerThread
    @POST("${BuildConfig.SERVICE_ENDPOINT}TripExecution/CancelTripExecutionInitial")
    suspend fun updateTripCancelBefore(
        @Header("Authorization") authorizationToken: String,
        @Query("driverId") driverId: Int,
        @Query("tripId") tripId: Int
    ): Response<TripUpdateResponse>

    @WorkerThread
    @GET
    suspend fun getDirectionsRoute(
        @Url url: String,
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("key") key: String
    ): Response<Directions>

    @WorkerThread
    @GET
    suspend fun getMultipleWayPointDirectionsAndRoutes(
        @Url url: String,
        @Query("mode") mode: String,
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("waypoints") wayPoints: String,
        @Query("key") key: String
    ): Response<Directions>

    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}TripExecution/GetPassengerOngoingTrip")
    suspend fun getOnGoingTripStatus(
        @Header("Authorization") authorizationToken: String,
        @Query("passengerID") passengerID: Int
    ): Response<OnGoingTripStatusResponse>

    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}TripExecution/Get")
    suspend fun getOnGoingTripData(
        @Header("Authorization") authorizationToken: String,
        @Query("Id") Id: String
    ): Response<TripExecutionDataResponse>

    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}TripExecution/GetDriverLastLocation")
    suspend fun getOnGoingTripDriverLocattion(
        @Header("Authorization") authorizationToken: String,
        @Query("tripExecutionId") Id: String
    ): Response<TripDriverCurrentLocation>

    @WorkerThread
    @PUT("${BuildConfig.SERVICE_ENDPOINT}TripExecution/UpdatePassengerBoardingStatus")
    suspend fun updateDestinationArrivedStatus(
        @Header("Authorization") authorizationToken: String,
        @Body requestBody: RequestBody
    ): Response<TripActionsPassengerResponse>

    @WorkerThread
    @POST("${BuildConfig.SERVICE_ENDPOINT}TripExecution/MarkPassengersDestinationArrived")
    suspend fun markDestinationArrived(
        @Header("Authorization") authorizationToken: String,
        @Body requestBody: RequestBody
    ): Response<TripUpdateResponse>

    @WorkerThread
    @POST("${BuildConfig.SERVICE_ENDPOINT}TripExecution/SendDriverUpdateFCM")
    suspend fun sendPassengersNotification(
        @Header("Authorization") authorizationToken: String,
        @Query("tripExecutionId") Id: String,
        @Body requestBody: RequestBody
    ): Response<TripUpdateResponse>

    @WorkerThread
    @POST("${BuildConfig.SERVICE_ENDPOINT}TripExecution/DriverArrivedFCM")
    suspend fun markDriverArrived(
        @Header("Authorization") authorizationToken: String,
        @Query("tripExecutionId") Id: String,
        @Body requestBody: RequestBody
    ): Response<TripUpdateResponse>

    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}TripExecution/GetDriverOngoingTrip")
    suspend fun getCurrentTripOnGoing(
        @Header("Authorization") authorizationToken: String,
        @Query("driverId") Id: Int
    ): Response<TripActiveResponse>

    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}TripExecution/GetTripHistoryForPassengerSpecific")
    suspend fun getPastTripDetails(
        @Header("Authorization") authorizationToken: String,
        @Query("tripExecutionPassengerId") tripExecutionPassengerId: String
    ): Response<PastScheduleDetailsResponse>

    @WorkerThread
    @POST("${BuildConfig.SERVICE_ENDPOINT}TripExecution/PassengerCancelTrip")
    suspend fun cancelScheduledTripPassenger(
        @Header("Authorization") authorizationToken: String,
        @Body requestBody: RequestBody
    ): Response<TripActionsPassengerResponse>

    @WorkerThread
    @POST("${BuildConfig.SERVICE_ENDPOINT}Firebase/CreateToken")
    suspend fun addFcmToken(
        @Header("Authorization") authorizationToken: String,
        @Query("userId") userId: Int,
        @Query("token") token: String,
        @Query("deviceId") deviceId: String
    ): Response<TripActionsPassengerResponse>

    @WorkerThread
    @DELETE("${BuildConfig.SERVICE_ENDPOINT}Firebase/DeleteToken")
    suspend fun removeFcmToken(
        @Header("Authorization") authorizationToken: String,
        @Query("userId") userId: Int,
        @Query("deviceId") deviceId: String
    ): Response<TripActionsPassengerResponse>


    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}Notification/GetAll")
    suspend fun getNotification(
        @Header("Authorization") authorizationToken: String,
        @Query("UserId") userId: Int,
        @Query("SkipCount") skipCount: Int,
        @Query("MaxResultCount") maxResultCount: Int
    ): Response<NotificationResponse>

    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}Request/GetUserRequests")
    suspend fun getALlRequestsList(
        @Header("Authorization") authorizationToken: String,
        @Query("userId") userId: Int,
        @Query("SkipCount") skipCount: Int,
        @Query("MaxResultCount") maxResultCount: Int
    ): Response<RequestsListResponse>

    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}Request/GetUserRequestSpecific")
    suspend fun getRequestDetails(
        @Header("Authorization") authorizationToken: String,
        @Query("id") requestId: String
    ): Response<RequestDetailsResponse>

    @WorkerThread
    @POST("${BuildConfig.SERVICE_ENDPOINT}Request/SendMessage")
    suspend fun postCommentMessage(
        @Header("Authorization") authorizationToken: String,
        @Body requestBody: RequestBody
    ): Response<UserRequestCommentResponse>

    @WorkerThread
    @DELETE("${BuildConfig.SERVICE_ENDPOINT}Request/DeleteRequest")
    suspend fun deleteUserRequestItem(
        @Header("Authorization") authorizationToken: String,
        @Query("id") requestId: String
    ): Response<UserRequestCommentResponse>


    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}CompanyEntity/GetAll")
    suspend fun getAllEntity(): Response<DepartmentResponse>

    @WorkerThread
    @GET("${BuildConfig.SERVICE_ENDPOINT}Department/GetAll")
    suspend fun getAllDepartment(
        @Query("companyEntityId") entityId: Int
    ): Response<DepartmentResponse>

    @WorkerThread
    @POST("${BuildConfig.SERVICE_ENDPOINT}Firebase/SendNotification")
    suspend fun sendChatNotification(
        @Header("Authorization") authorizationToken: String,
        @Query("userId") userId: Int,
        @Query("title") title: String,
        @Query("body") body: String,
        @Body requestBody: RequestBody
    ): Response<ChatNotificationResponse>
}