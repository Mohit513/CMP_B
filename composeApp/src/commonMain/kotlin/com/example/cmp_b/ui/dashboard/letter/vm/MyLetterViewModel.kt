package com.example.cmp_b.ui.dashboard.letter.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cmp_b.composeapp.generated.resources.Res
import cmp_b.composeapp.generated.resources.ic_driving_licence
import cmp_b.composeapp.generated.resources.ic_form_16
import cmp_b.composeapp.generated.resources.ic_inc_letter
import cmp_b.composeapp.generated.resources.ic_offer_letter
import cmp_b.composeapp.generated.resources.ic_other_letter
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


class MyLetterViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(MyLetterUiState())
    val uiState: StateFlow<MyLetterUiState> = _uiState

    private val _navigation = Channel<NavigationEvent>()
    val navigation = _navigation.receiveAsFlow()

    init {
        fetchMyLettersItems()
    }

    private fun sendNavigation(event: NavigationEvent) {
        viewModelScope.launch {
            _navigation.send(event)
        }
    }

    fun onEvent(event: MyLetterEvent) {

        when (event) {

            MyLetterEvent.OnBackClick -> {
                sendNavigation(NavigationEvent.PopBack)
            }

            is MyLetterEvent.OnItemClick -> {

                when (event.item.id) {

                    "1" -> {
                        sendNavigation(
                            NavigationEvent.Navigate(
                                NavRoutes.CandidateLoiScreen.route
                            )
                        )
                    }

                    "2" -> {
                        sendNavigation(
                            NavigationEvent.Navigate(
                                NavRoutes.OfferLetterScreen.route
                            )
                        )
                    }

                    "3" -> {
                        sendNavigation(
                            NavigationEvent.Navigate(
                                NavRoutes.OtherLetterScreen.route
                            )
                        )
                    }

                    "4" -> {
                        sendNavigation(
                            NavigationEvent.Navigate(
                                NavRoutes.FormSixteenScreen.route
                            )
                        )
                    }

                    "5" -> {
                        sendNavigation(
                            NavigationEvent.Navigate(
                                NavRoutes.IncrementLetterScreen.route
                            )
                        )
                    }
                }
            }
        }
    }

    fun fetchMyLettersItems() {

        viewModelScope.launch {

            _uiState.update {
                it.copy(isLoading = true, error = null)
            }

            try {

                delay(1000)

                val dummyData = listOf(

                    MyLetterItem(
                        id = "1",
                        title = "Candidate LOI",
                        icon = Res.drawable.ic_driving_licence
                    ),

                    MyLetterItem(
                        id = "2",
                        title = "Offer Letter",
                        icon = Res.drawable.ic_offer_letter
                    ),

                    MyLetterItem(
                        id = "3",
                        title = "Other Letter",
                        icon = Res.drawable.ic_other_letter
                    ),

                    MyLetterItem(
                        id = "4",
                        title = "Form 16",
                        icon = Res.drawable.ic_form_16
                    ),

                    MyLetterItem(
                        id = "5",
                        title = "Increment Letter",
                        icon = Res.drawable.ic_inc_letter
                    ),

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

data class MyLetterUiState(
    val isLoading: Boolean = false,

    val items: List<MyLetterItem> = emptyList(),

    val error: String? = null,

    )

data class MyLetterItem(

    val id: String,

    val title: String,

    val isCompleted: Boolean = false,

 val icon: DrawableResource
)

sealed class MyLetterEvent {

    object OnBackClick : MyLetterEvent()

    data class OnItemClick(val item: MyLetterItem) : MyLetterEvent()
}
