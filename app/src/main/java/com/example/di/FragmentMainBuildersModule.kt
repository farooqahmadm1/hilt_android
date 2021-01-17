package com.ibex.fleetmanager.driver.di

import com.ibex.fleetmanager.driver.ui.change_password.ChangePasswordFragment
import com.ibex.fleetmanager.driver.ui.chat.inbox.InboxFragment
import com.ibex.fleetmanager.driver.ui.home.HomeFragment
import com.ibex.fleetmanager.driver.ui.notification.NotificationFragment
import com.ibex.fleetmanager.driver.ui.profile.profile_edit.ProfileEditFragment
import com.ibex.fleetmanager.driver.ui.profile.profile_view.ProfileFragment
import com.ibex.fleetmanager.driver.ui.schdule.past.PastFragment
import com.ibex.fleetmanager.driver.ui.schdule.ScheduleFragment
import com.ibex.fleetmanager.driver.ui.schdule.past.PastDetailFragment
import com.ibex.fleetmanager.driver.ui.schdule.upcoming.UpcomingDetailFragment
import com.ibex.fleetmanager.driver.ui.schdule.upcoming.UpcomingFragment
import com.ibex.fleetmanager.driver.ui.trip.BoardingStatusFragment
import com.ibex.fleetmanager.driver.ui.trip.CallUserFragment
import com.ibex.fleetmanager.driver.ui.trip.ChatFragment
import com.ibex.fleetmanager.driver.ui.vehicle.UpdateVehicleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentMainBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileEditFragment(): ProfileEditFragment

    @ContributesAndroidInjector
    abstract fun contributePastFragment(): PastFragment

    @ContributesAndroidInjector
    abstract fun contributeUpcomingFragment(): UpcomingFragment

    @ContributesAndroidInjector
    abstract fun contributeScheduleFragment(): ScheduleFragment

    @ContributesAndroidInjector
    abstract fun contributeBoardingStatusFragment(): BoardingStatusFragment

    @ContributesAndroidInjector
    abstract fun contributeCallUserFragment(): CallUserFragment

    @ContributesAndroidInjector
    abstract fun contributeUpcomingDetailFragment(): UpcomingDetailFragment

    @ContributesAndroidInjector
    abstract fun contributePastDetailFragment(): PastDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeVehicleUpdateFragment(): UpdateVehicleFragment

    @ContributesAndroidInjector
    abstract fun contributeChangePasswordFragment(): ChangePasswordFragment

    @ContributesAndroidInjector
    abstract fun contributeNotificationFragment(): NotificationFragment

    @ContributesAndroidInjector
    abstract fun contributeChatFragment(): ChatFragment

    @ContributesAndroidInjector
    abstract fun contributeInboxFragment(): InboxFragment

    @ContributesAndroidInjector
    abstract fun contributeInboxChatFragment(): com.ibex.fleetmanager.driver.ui.chat.chat.ChatFragment

}