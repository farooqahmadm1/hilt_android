package com.ibex.fleetmanager.common.utility

enum class PreferenceEnums(val key: String) {
    USER_ID("user_id"),
    ACCESS_TOKEN("access_token"),
    IS_LOGGED_IN("is_logged_in"),
    FIREBASE_TOKEN("firebase_token"),
    MAC_ADDRESS("mac_address"),
    IS_FCM_BINDED("is_fcm_binded"),
    IS_Mock_Enabled("is_mock_enabled")
}

enum class TripEnums(val key: String) {
    CURRENT_TRIP_ID("trip_id"),
    IS_TRIP_ACTIVE("is_trip_active"),
    CURRENT_TRIP_TYPE("trip_type")
}

enum class ChatEnums(val key: String) {
    IS_CHAT_SCREEN("is_chat_screen")
}

enum class DriverEnums(val key: String) {
    PROFILE_FRAGMENT_VISIBLE("profile_toolbar_item")
}

enum class TripType(val value: Int) {
    PICK_UP(1),
    DROP_OFF(2)
}

enum class BoardStatus(val value: Int) {
    PENDING(1),
    CHECK_IN(2),
    MISSED(3),
    DROPPED(4),
    PASSENGER_CANCEL(5),
    PASSENGER_COMPLETE_CONFIRMATION(6),
    PASSENGER_CANCEL_CONFIRMATION(7),
    DESTINATION_ARRIVED(8),
    DRIVER_CANCELED(9)
}

enum class TripStatus(val value: Int) {
    IN_PROGRESS(1),
    COMPLETED(2),
    CANCELED(3),
    DESTINATION_ARRIVED(4)
}

enum class MediaType(val value: Int) {
    PROFILE_IMAGE(1),
    LICENSE_IMAGE(3)
}

enum class Role(val value: Int) {
    PASSENGER(3),
    DRIVER(2)
}

enum class ScheduleDays(val value: Int) {
    MONDAY_PICKUP(1),
    MONDAY_DROPOFF(2),
    TUESDAY_PICKUP(3),
    TUESDAY_DROPOFF(4),
    WEDNESDAY_PICKUP(5),
    WEDNESDAY_DROPOFF(6),
    THURSDAY_PICKUP(7),
    THURSDAY_DROPOFF(8),
    FRIDAY_PICKUP(9),
    FRIDAY_DROPOFF(10),
    SATURDAY_PICKUP(11),
    SATURDAY_DROPOFF(12),
    SUNDAY_PICKUP(13),
    SUNDAY_DROPOFF(14)

}

enum class ServiceType(val service: Int) {
    PICK_UP(1),
    DROP_OFF(2),
    TRIP_BOTH(3)
}

enum class MarkerType(val value: Int) {
    START(1),
    END(2),
    STOP(3)
}

enum class UserHomeViewStatus(val view: Int) {
    NO_ACTIVE_TRIP(0),
    IN_PROGRESS_TRIP(1),
    IN_PROGRESS_PENDING_CHECKEDIN(2),// show trip execution and path
    DRIVER_ARRIVED_TRIP(3),// show trip execution and path
    CHECKED_IN_TRIP(4),// show trip execution and path
    COMPLETED_TRIP(5),
    CANCELED_TRIP(6),
    DESITINATION_ARRIVED(7)// show trip execution and path

}

enum class TripExecutionPassengerStatus(val value: Int) {
    INPROGRESS(1),
    COMPLETED(2),
    CANCELLED(3)
}

enum class TripExecutionDriverStatus(val value: Int) {
    PENDING(1),
    CHECKIN(2),
    MISSED(3),
    DROPPED(4),
    PASSENGERCANCEL(5),
    PASSENGERCOMPLETECONFIRMATION(6),
    PASSENGERCANCELCONFIRMATION(7),
    DESTINATIONARRIVED(8),
    DRIVERCANCELLED(9)
}

enum class ScheduleTripStatus(val status: String) {
    UPCOMING("upcoming"),
    PENDING("pending"),
    CANCEL("cancelled")
}

enum class PastScheduleTripStatus(val status: Int) {
    INPROGRESS(1),
    COMPLETED(2),
    CANCELLED(3),
    DRIVERCANCELLED(4)
}

enum class FCMSTATUS(val value: Int) {
    GENERAL(0),
    TRIPSTARTED(1),
    CHECKIN(2),
    MISSED(3),
    DESTINATIONARRIVED(4),
    DRIVERARRIVED(5),
    CANCELCONFIRMATION(6),
    COMPLETECONFIRMATION(7),
    DriverVehicleUpdated(8),
    DriverNewTripAssigned(9),
    DriverTripCanceled(10),
    PassengerScheduleApproved(11),
    PassengerScheduleRejected(12),
    PassengerScheduleUpdateRequest(13),
    PassengerTripCancelledByDriver(14),
    RequestCreated(15),
    RequestApproved(16),
    RequestRejected(17),
    AdminReplied(18),
    UpcomingTripDriver(19),
    CHATNOTIFICATION(20)
}

enum class FcmDestinationsLocal(val value: Int) {
    HOME(1),
    PROFILE(2),
    SCHEDULE(3)
}

enum class FCMLOCALBORADCASTTYPES(val type: String) {
    TRIPEXECUTIONS("tripexecutions"),
    REQUESTS("requests")
}

enum class MarkerWindowsTypes(val type: Int) {
    START_CAP(1),
    END_CAP(2)
}

enum class ScheduleStatus(val status: Int) {
    PENDING(1),
    APPROVED(2)
}

enum class ScheduleEntryFields(val entry: Int) {
    PICKUP_TIME(1),
    PICKUP_TRIP(2),
    DROPOFF_TIME(3),
    DROPOFF_TRIP(4)
}

enum class RequestCommentUserType(val value: String) {
    ADMIN("Admin"),
    USER("User")
}

enum class UserRequestCategory(val value: Int) {
    PASSENGER(1),
    DRIVER(2),
    SCHEDULE(3),
    TRIP(4)
}

enum class UserRequestSubCategory(val value: Int) {
    PASSENGERPICKUPLOCATIONCHANGE(1), // Location Change Request (Pickup & Dropoff)
    PASSENGERROUTECHANGE(2), // Route Change Request
    PASSENGERROUTEANDADDRESSCHANGE(3), // Location & Route Change Request
    SCHEDULEUPDATE(4),
    SCHEDULESERVICETYPEUPDATE(5)
}

enum class UserRequestStatus(val type: Int) {
    PENDING(1),
    COMPLETED(2),
    WITHDRAWNBYUSER(3),
    REJECTED(4)
}

enum class SettingsEnumValues(val settings: String) {
    PASENEGER_ROUTES("AppPassengerRouteSelection"),
    PASSENGER_TRIPS("AppPassengerTripSelection")
}


enum class LocationRequestType(val type: Int) {
    ARRIVED_DESTINATION(1),
    LOCATION_UPDATE(2)
}