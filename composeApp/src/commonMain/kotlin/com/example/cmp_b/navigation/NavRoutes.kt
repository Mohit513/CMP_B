package com.example.cmp_b.navigation

sealed class NavRoutes(val route: String) {
    object Login : NavRoutes("login")
    object DigiDashboard : NavRoutes("dashboard")
    object PostList : NavRoutes("post_list")

    // Dashboard menu placeholder routes
    object DigiOnBoarding : NavRoutes("digi_onboarding")
    object ProfileScreen : NavRoutes("profile")
    object MyLettersScreen : NavRoutes("my_letters")
    object AttendanceScreen : NavRoutes("attendance")
    object DummyScreen : NavRoutes("dummy")
    object PayslipScreen : NavRoutes("payslip")
    object PfListScreen : NavRoutes("pf_list")
    object ReimbursementScreen : NavRoutes("reimbursement")
    object DocumentDetailsScreen : NavRoutes("document_details")
    object NotificationScreen : NavRoutes("notification")
    object IdCardScreen : NavRoutes("id_card")
    object AadhaarDetails : NavRoutes("aadhaar_details")
    object BankDetails : NavRoutes("bank_details")
}

sealed class NavigationEvent {
    data class Navigate(val route: String) : NavigationEvent()
    object PopBack : NavigationEvent()
    data class PopBackWithResult(val key: String, val value: Any) : NavigationEvent()
    data class ClearBackStackAndNavigate(val route: String) : NavigationEvent()
}
