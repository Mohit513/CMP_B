package com.example.cmp_b.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cmp_b.core.data.session.SessionManager
import com.example.cmp_b.ui.auth.LoginScreen
import com.example.cmp_b.ui.auth.LoginViewModel
import com.example.cmp_b.ui.dashboard.DashboardViewModel
import com.example.cmp_b.ui.dashboard.DigiDashboardScreen
import com.example.cmp_b.ui.dashboard.document_details.DocumentDetailsScreen
import com.example.cmp_b.ui.dashboard.document_details.vm.DocumentDetailsViewModel
import com.example.cmp_b.ui.dashboard.letter.vm.MyLetterViewModel
import com.example.cmp_b.ui.dashboard.onboarding.DigiOnboardingScreen
import com.example.cmp_b.ui.dashboard.onboarding.sub_screens.aadhar.AadharDetailsScreen
import com.example.cmp_b.ui.dashboard.onboarding.sub_screens.aadhar.AadharDetailsViewModel
import com.example.cmp_b.ui.dashboard.onboarding.sub_screens.bank_details.BankDetailsScreen
import com.example.cmp_b.ui.dashboard.onboarding.sub_screens.bank_details.BankDetailsViewModel
import com.example.cmp_b.ui.dashboard.payslip.PayslipScreen
import com.example.cmp_b.ui.dashboard.payslip.vm.PayslipViewModel
import com.example.cmp_b.ui.dashboard.profile.EditProfileScreen
import com.example.cmp_b.ui.dashboard.profile.ProfileScreen
import com.example.cmp_b.ui.dashboard.profile.vm.ProfileViewModel
import com.example.cmp_b.ui.post_list.PostListScreen
import com.example.cmp_b.ui.post_list.PostViewModel
import com.example.cmp_b.ui.dashboard.letter.MyLetterScreen
import com.example.cmp_b.ui.dashboard.letter.feature.other_letter.vm.OtherLetterViewModel
import com.example.cmp_b.ui.dashboard.notification.NotificationScreen
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NavGraph(appState: AppState) {
    val sessionManager: SessionManager = koinInject()
    val startDestination = if (sessionManager.isLoggedIn) {
        NavRoutes.DigiDashboard.route
    } else {
        NavRoutes.DigiDashboard.route
    }

    Scaffold(
        snackbarHost = { SnackbarHost(appState.snackbarHostState) }
    ) { innerPadding ->
        NavHost(
            navController = appState.navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavRoutes.Login.route) {
                val viewModel: LoginViewModel = koinViewModel()
                LoginScreen(appState, viewModel)
            }
            composable(NavRoutes.PostList.route) {
                val viewModel: PostViewModel = koinViewModel()
                PostListScreen(viewModel)
            }
            composable(NavRoutes.DigiDashboard.route) {
                val viewModel: DashboardViewModel = koinViewModel()
                DigiDashboardScreen(appState, viewModel)
            }
            composable(NavRoutes.DigiOnBoarding.route) {
                DigiOnboardingScreen(appState)
            }
            composable(NavRoutes.AadhaarDetails.route) {
                val viewModel: AadharDetailsViewModel = koinViewModel()
                AadharDetailsScreen(appState, viewModel)
            }
            composable(NavRoutes.BankDetails.route) {
                val viewModel: BankDetailsViewModel = koinViewModel()
                BankDetailsScreen(appState, viewModel)
            }
            composable(NavRoutes.ProfileScreen.route) { backStackEntry ->

                val profileViewModel: ProfileViewModel = koinViewModel()

                ProfileScreen(
                    appState = appState,
                    profileViewModel = profileViewModel
                )
            }
            composable(NavRoutes.EditProfileScreen.route) { backStackEntry ->

                val parentEntry = remember(backStackEntry) {
                    appState.navController.getBackStackEntry(NavRoutes.ProfileScreen.route)
                }

                val profileViewModel: ProfileViewModel = koinViewModel(viewModelStoreOwner = parentEntry)

                EditProfileScreen(
                    appState = appState,
                    profileViewModel = profileViewModel
                )
            }
            composable(NavRoutes.PayslipScreen.route){
                val viewModel = PayslipViewModel()
                PayslipScreen(appState, viewModel)
            }
            composable(NavRoutes.MyLettersScreen.route){
                val viewModel: MyLetterViewModel = koinViewModel()
                MyLetterScreen(appState, viewModel)
            }
            composable(NavRoutes.OfferLetterScreen.route){
                val viewModel: com.example.cmp_b.ui.dashboard.letter.feature.offer_letter.vm.OfferLetterViewModel = koinViewModel()
                com.example.cmp_b.ui.dashboard.letter.feature.offer_letter.OfferLetterScreen(appState, viewModel)
            }
            composable(NavRoutes.OtherLetterScreen.route){
                val viewModel: OtherLetterViewModel = koinViewModel()
                com.example.cmp_b.ui.dashboard.letter.feature.other_letter.OtherLetterScreen(appState, viewModel)
            }
            composable(NavRoutes.DocumentDetailsScreen.route){
                val viewModel: DocumentDetailsViewModel = koinViewModel()
                DocumentDetailsScreen(appState, viewModel)
            }
            composable(NavRoutes.NotificationScreen.route){
                NotificationScreen(appState)
            }
        }
    }
}
