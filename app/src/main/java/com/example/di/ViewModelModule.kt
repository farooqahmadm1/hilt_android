package com.ibex.fleetmanager.driver.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ibex.fleetmanager.common.di.ViewModelKey
import com.ibex.fleetmanager.common.di.ViewModelProviderFactory
import com.ibex.fleetmanager.driver.ui.auth.forgot_password.ForgotViewModel
import com.ibex.fleetmanager.driver.ui.auth.login.LoginViewModel
import com.ibex.fleetmanager.driver.ui.auth.register.SignUpViewModel
import com.ibex.fleetmanager.driver.ui.change_password.data.ChangePassViewModel
import com.ibex.fleetmanager.driver.ui.chat.data.ChatViewModel
import com.ibex.fleetmanager.driver.ui.home.HomeViewModel
import com.ibex.fleetmanager.driver.ui.notification.data.NotificationViewModel
import com.ibex.fleetmanager.driver.ui.profile.data.ProfileViewModel
import com.ibex.fleetmanager.driver.ui.schdule.ScheduleViewModel
import com.ibex.fleetmanager.driver.ui.vehicle.data.VehicleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSignUpViewModel(signUpViewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ForgotViewModel::class)
    abstract fun bindForgotViewModel(forgotViewModel: ForgotViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScheduleViewModel::class)
    abstract fun bindScheduleViewModel(scheduleViewModel: ScheduleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VehicleViewModel::class)
    abstract fun bindVehicleViewModel(vehicleViewModel: VehicleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChangePassViewModel::class)
    abstract fun bindChangePassViewModel(changePassViewModel: ChangePassViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NotificationViewModel::class)
    abstract fun bindNotificationViewModel(notificationViewModel: NotificationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChatViewModel::class)
    abstract fun bindChatViewModel(chatViewModel: ChatViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}