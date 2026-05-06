package com.example.cmp_b.navigation

sealed class NavRoutes(val route: String) {
    object Login : NavRoutes("login")
    object DigiDashboard : NavRoutes("dashboard")
    object PostList : NavRoutes("post_list")
}

sealed class NavigationEvent {
    data class Navigate(val route: String) : NavigationEvent()
    object PopBack : NavigationEvent()
    data class PopBackWithResult(val key: String, val value: Any) : NavigationEvent()
    data class ClearBackStackAndNavigate(val route: String) : NavigationEvent()
}
