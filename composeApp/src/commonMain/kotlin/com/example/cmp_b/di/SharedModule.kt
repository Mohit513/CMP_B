package com.example.cmp_b.di

import com.example.cmp_b.core.data.repository.AuthRepositoryImpl
import com.example.cmp_b.shared.data.mapper.CandidateLoginMapper
import com.example.cmp_b.shared.data.mapper.LoginOtpValidateMapper
import com.example.cmp_b.shared.data.mapper.PostMapper
import com.example.cmp_b.shared.data.repository.PostRepositoryImpl
import com.example.cmp_b.shared.domain.repository.AuthRepository
import com.example.cmp_b.shared.domain.repository.PostRepository
import com.example.cmp_b.shared.domain.usecase.CandidateLoginUseCase
import com.example.cmp_b.shared.domain.usecase.GetPostsUseCase
import com.example.cmp_b.shared.domain.usecase.LoginOtpValidateUseCase
import com.example.cmp_b.core.data.session.SessionManager
import com.example.cmp_b.ui.auth.LoginViewModel
import com.example.cmp_b.ui.dashboard.DashboardViewModel
import com.example.cmp_b.ui.dashboard.letter.feature.offer_letter.vm.OfferLetterViewModel
import com.example.cmp_b.ui.dashboard.letter.feature.other_letter.vm.OtherLetterViewModel
import com.example.cmp_b.ui.dashboard.letter.vm.MyLetterViewModel
import com.example.cmp_b.ui.dashboard.onboarding.DigiOnboardingViewModel
import com.example.cmp_b.ui.dashboard.onboarding.sub_screens.aadhar.AadharDetailsViewModel
import com.example.cmp_b.ui.dashboard.onboarding.sub_screens.bank_details.BankDetailsViewModel
import com.example.cmp_b.ui.dashboard.profile.vm.ProfileViewModel
import com.example.cmp_b.ui.post_list.PostViewModel
import com.russhwolf.settings.*
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule = module {
    // Mappers
    factoryOf(::PostMapper)
    factoryOf(::CandidateLoginMapper)
    factoryOf(::LoginOtpValidateMapper)

    // Repository - Bound to its interface
    singleOf(::PostRepositoryImpl) bind PostRepository::class
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class

    // Use Cases - Defined as factory since they are stateless
    factoryOf(::GetPostsUseCase)
    factoryOf(::CandidateLoginUseCase)
    factoryOf(::LoginOtpValidateUseCase)

    // Session / Local Storage (multiplatform-settings)
    // Uses SharedPreferences on Android, NSUserDefaults on iOS
    single { Settings() }
    single { SessionManager(get()) }

    // ViewModels - Defined as factory in commonMain for cross-platform support.
    // On Android, you can use koinViewModel() in Compose to get these.
    factoryOf(::PostViewModel)
    factoryOf(::LoginViewModel)
    factoryOf(::DashboardViewModel)
    factoryOf(::DigiOnboardingViewModel)
    factoryOf(::AadharDetailsViewModel)
    factoryOf(::BankDetailsViewModel)
    factoryOf(::ProfileViewModel)

    //my letter screen view models
    factoryOf(::MyLetterViewModel)
    factoryOf(::OtherLetterViewModel)
    factoryOf(::OfferLetterViewModel)

}


