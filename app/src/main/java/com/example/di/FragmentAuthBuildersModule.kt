package com.ibex.fleetmanager.driver.di

import com.ibex.fleetmanager.driver.ui.auth.forgot_password.ForgetPasswordFragment
import com.ibex.fleetmanager.driver.ui.auth.forgot_password.PasswordSucessFragment
import com.ibex.fleetmanager.driver.ui.auth.forgot_password.ResetPasswordFragment
import com.ibex.fleetmanager.driver.ui.auth.login.LoginFragment
import com.ibex.fleetmanager.driver.ui.auth.register.SignUpTwoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentAuthBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeSignUpTwoFragment(): SignUpTwoFragment

    @ContributesAndroidInjector
    abstract fun contributeResetFragment(): ResetPasswordFragment

    @ContributesAndroidInjector
    abstract fun contributeForgetPasswordFragment(): ForgetPasswordFragment

    @ContributesAndroidInjector
    abstract fun contributePasswordSuccessFragment(): PasswordSucessFragment
}