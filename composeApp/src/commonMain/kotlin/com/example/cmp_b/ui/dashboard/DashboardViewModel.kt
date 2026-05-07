package com.example.cmp_b.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmp_b.navigation.NavRoutes
import com.example.cmp_b.navigation.NavigationEvent
import com.example.cmp_b.ui.components.DashboardItem
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState

    private val _navigation = Channel<NavigationEvent>()
    val navigation = _navigation.receiveAsFlow()

    private fun sendNavigation(event: NavigationEvent) {
        viewModelScope.launch {
            _navigation.send(event)
        }
    }

    fun loadDashboardItems(items: List<DashboardItem>) {
        _uiState.update {
            it.copy(
                dashboardItems = items,
                isLoading = false,
                error = null
            )
        }
    }

    fun onEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.OnMenuClick -> {
                sendNavigation(
                    NavigationEvent.Navigate(
                        event.item.route ?: NavRoutes.DigiDashboard.route
                    )
                )
            }
            DashboardEvent.OnProfileClick -> {
                sendNavigation(
                    NavigationEvent.Navigate(
                        NavRoutes.ProfileScreen.route
                    )
                )
            }
            DashboardEvent.OnAttendanceClick -> {
                sendNavigation(
                    NavigationEvent.ClearBackStackAndNavigate(
                        NavRoutes.AttendanceScreen.route
                    )
                )
            }
            DashboardEvent.MyLetterClick -> {
                sendNavigation(
                    NavigationEvent.ClearBackStackAndNavigate(
                        NavRoutes.MyLettersScreen.route
                    )
                )
            }
            DashboardEvent.RefreshDashboard -> {
                _uiState.update {
                    it.copy(
                        isLoading = true,
                        error = null
                    )
                }
            }
            DashboardEvent.OnNotificationClick -> {
                sendNavigation(
                    NavigationEvent.Navigate(
                        NavRoutes.NotificationScreen.route
                    )
                )
            }
        }
    }

    private fun handleMenuClick(item: DashboardItem) {
        when (item.title) {
            "Onboarding" -> {
                sendNavigation(
                    NavigationEvent.Navigate(
                        NavRoutes.DigiOnBoarding.route
                    )
                )
            }
            "Profile" -> {
                sendNavigation(
                    NavigationEvent.Navigate(
                        NavRoutes.ProfileScreen.route
                    )
                )
            }
            "Attendance" -> {
                sendNavigation(
                    NavigationEvent.Navigate(
                        NavRoutes.AttendanceScreen.route
                    )
                )
            }
            "my_letters_screen" -> {
                sendNavigation(
                    NavigationEvent.Navigate(
                        NavRoutes.MyLettersScreen.route
                    )
                )
            }
            else -> {
                println("Clicked Menu: ${item.title}")
            }
        }
    }
}

data class DashboardUiState(
    val isLoading: Boolean = false,
    val userName: String = "James",
    val dashboardItems: List<DashboardItem> = emptyList(),
    val error: String? = null
)

sealed class DashboardEvent {
    data class OnMenuClick(val item: DashboardItem) : DashboardEvent()
    object OnProfileClick : DashboardEvent()
    object OnAttendanceClick : DashboardEvent()
    object RefreshDashboard : DashboardEvent()
    object MyLetterClick : DashboardEvent()
    object OnNotificationClick : DashboardEvent()
}
