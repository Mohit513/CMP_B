package com.example.cmp_b.ui.dashboard.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cmp_b.composeapp.generated.resources.Res
import cmp_b.composeapp.generated.resources.ic_candidate_details
import cmp_b.composeapp.generated.resources.ic_document_details
import cmp_b.composeapp.generated.resources.ic_driving_licence
import cmp_b.composeapp.generated.resources.ic_education_details
import cmp_b.composeapp.generated.resources.ic_epf_details
import cmp_b.composeapp.generated.resources.ic_esic_details
import cmp_b.composeapp.generated.resources.ic_family_details
import cmp_b.composeapp.generated.resources.ic_finger_scan
import cmp_b.composeapp.generated.resources.ic_kyc
import cmp_b.composeapp.generated.resources.ic_pf_uan
import cmp_b.composeapp.generated.resources.ic_signature
import cmp_b.composeapp.generated.resources.ic_workexp
import com.example.cmp_b.navigation.NavRoutes
import com.example.cmp_b.navigation.NavigationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource

class DigiOnboardingViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DigiOnboardingUiState())
    val uiState: StateFlow<DigiOnboardingUiState> = _uiState

    private val _navigation = Channel<NavigationEvent>()
    val navigation = _navigation.receiveAsFlow()

    init {
        fetchOnboardingItems()
    }

    private fun sendNavigation(event: NavigationEvent) {
        viewModelScope.launch {
            _navigation.send(event)
        }
    }

    fun onEvent(event: OnBoardingEvent) {

        when (event) {

            OnBoardingEvent.OnBackClick -> {
                sendNavigation(NavigationEvent.PopBack)
            }

            is OnBoardingEvent.OnItemClick -> {

                when (event.item.id) {

                    "1" -> {
                        sendNavigation(
                            NavigationEvent.Navigate(
                                NavRoutes.AadhaarDetails.route
                            )
                        )
                    }

                    "2" -> {
                        sendNavigation(
                            NavigationEvent.Navigate(
                                NavRoutes.BankDetails.route
                            )
                        )
                    }

                    "3" -> {
                        sendNavigation(
                            NavigationEvent.Navigate(
                                NavRoutes.BankDetails.route
                            )
                        )
                    }

                    "4" -> {
                        sendNavigation(
                            NavigationEvent.Navigate(
                                NavRoutes.BankDetails.route
                            )
                        )
                    }

                    "5" -> {
                        sendNavigation(
                            NavigationEvent.Navigate(
                                NavRoutes.BankDetails.route
                            )
                        )
                    }

                    "6" -> {
                        sendNavigation(
                            NavigationEvent.Navigate(
                                NavRoutes.BankDetails.route
                            )
                        )
                    }

                    "7" -> {
                        sendNavigation(
                            NavigationEvent.Navigate(
                                NavRoutes.BankDetails.route
                            )
                        )
                    }

                    "8" -> {
                        sendNavigation(
                            NavigationEvent.Navigate(
                                NavRoutes.BankDetails.route
                            )
                        )
                    }

                    else -> {
                        // Future screens
                    }
                }
            }
        }
    }

    fun fetchOnboardingItems() {

        viewModelScope.launch {

            _uiState.update {
                it.copy(isLoading = true, error = null)
            }

            try {

                delay(1000)

                val dummyData = listOf(

                    OnboardingItem(
                        id = "1",
                        title = "Aadhar verification",
                        icon = Res.drawable.ic_finger_scan
                    ),

                    OnboardingItem(
                        id = "2",
                        title = "KYC",
                        icon = Res.drawable.ic_kyc
                    ),

                    OnboardingItem(
                        id = "3",
                        title = "Candidate details",
                        icon = Res.drawable.ic_candidate_details
                    ),

                    OnboardingItem(
                        id = "4",
                        title = "Driving license",
                        icon = Res.drawable.ic_driving_licence
                    ),

                    OnboardingItem(
                        id = "5",
                        title = "Family details",
                        icon = Res.drawable.ic_family_details
                    ),

                    OnboardingItem(
                        id = "6",
                        title = "Education details",
                        icon = Res.drawable.ic_education_details
                    ),

                    OnboardingItem(
                        id = "7",
                        title = "Work experience",
                        icon = Res.drawable.ic_workexp
                    ),

                    OnboardingItem(
                        id = "8",
                        title = "EPF details",
                        icon = Res.drawable.ic_epf_details
                    ),

                    OnboardingItem(
                        id = "9",
                        title = "ESIC details",
                        icon = Res.drawable.ic_esic_details
                    ),

                    OnboardingItem(
                        id = "10",
                        title = "Document details",
                        icon = Res.drawable.ic_document_details
                    ),

                    OnboardingItem(
                        id = "11",
                        title = "Signature",
                        icon = Res.drawable.ic_signature
                    ),

                    OnboardingItem(
                        id = "12",
                        title = "PF/UAN",
                        icon = Res.drawable.ic_pf_uan
                    )
                )

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        items = dummyData
                    )
                }

            } catch (e: Exception) {

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Something went wrong"
                    )
                }
            }
        }
    }
}

data class DigiOnboardingUiState(

    val isLoading: Boolean = false,

    val items: List<OnboardingItem> = emptyList(),

    val error: String? = null
)

data class OnboardingItem(

    val id: String,

    val title: String,

    val isCompleted: Boolean = false,

    val icon: DrawableResource
)

sealed class OnBoardingEvent {

    object OnBackClick : OnBoardingEvent()

    data class OnItemClick(val item: OnboardingItem) : OnBoardingEvent()
}