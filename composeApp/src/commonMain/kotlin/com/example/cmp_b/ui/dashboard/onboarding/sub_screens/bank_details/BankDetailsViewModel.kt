package com.example.cmp_b.ui.dashboard.onboarding.sub_screens.bank_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmp_b.navigation.NavigationEvent
import com.example.cmp_b.ui.components.RadioOption
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BankDetailsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(BankDetailsUiState())
    val uiState: StateFlow<BankDetailsUiState> = _uiState

    private val _uiEvent = Channel<String>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _navigation = Channel<NavigationEvent>()
    val navigation = _navigation.receiveAsFlow()

    val options = listOf(
        RadioOption(1, "Yes"),
        RadioOption(2,"No")
    )

    fun onEvent(event: BankDetailsEvent){

        when(event){

            is BankDetailsEvent.OnAccountNumberChanged -> {
                _uiState.update {
                    it.copy(accountNumber = event.value)
                }
            }

            is BankDetailsEvent.OnRadioChanged -> {
                _uiState.update {
                    it.copy(radioSelection = event.value)
                }
            }

            is BankDetailsEvent.OnDateSelected -> {
                _uiState.update {
                    it.copy(date = event.value)
                }
            }

            is BankDetailsEvent.OnDropdownChanged -> {
                _uiState.update {
                    it.copy(dropdown = event.value)
                }
            }

            is BankDetailsEvent.OnFileSelected -> {

                if(event.uri.isEmpty()){

                    _uiState.update {
                        it.copy(
                            fileUri = null,
                            fileName = null,
                            fileSize = null
                        )
                    }

                }else{

                    val name = event.uri.substringAfterLast('/')

                    _uiState.update {
                        it.copy(
                            fileUri = event.uri,
                            fileName = if (name.isNotEmpty()) name else "Document",
                            fileSize = "Selected"
                        )
                    }
                }
            }
            BankDetailsEvent.OnSubmitClick -> {

                if(!validateFields()) return

                viewModelScope.launch {
                    _navigation.send(
                        NavigationEvent.Navigate("next_screen")
                    )
                }
            }

            BankDetailsEvent.OnBackClick -> {
                viewModelScope.launch {
                    _navigation.send(NavigationEvent.PopBack)
                }
            }
        }
    }

    private fun showSnackBar(message:String){
        viewModelScope.launch {
            _uiEvent.send(message)
        }
    }

    private fun validateFields():Boolean{

        val state = _uiState.value

        if(state.accountNumber.isBlank()){
            showSnackBar("Enter account number")
            return false
        }

        if(state.date.isBlank()){
            showSnackBar("Please Select date")
            return false
        }

        if(state.dropdown.isBlank()){
            showSnackBar("Please Select dropdown value")
            return false
        }

        if(state.fileUri == null){
            showSnackBar("Please Upload document")
            return false
        }

        return true
    }
}
data class BankDetailsUiState(

    val accountNumber:String = "",
    val radioSelection:Int = 1,
    val date:String = "",
    val dropdown:String = "",

    val fileUri:String? = null,
    val fileName:String? = null,
    val fileSize:String? = null
)


sealed class BankDetailsEvent{

    data class OnAccountNumberChanged(val value:String):BankDetailsEvent()

    data class OnRadioChanged(val value:Int):BankDetailsEvent()

    data class OnDateSelected(val value:String):BankDetailsEvent()

    data class OnDropdownChanged(val value:String):BankDetailsEvent()

    data class OnFileSelected(val uri:String):BankDetailsEvent()

    object OnSubmitClick:BankDetailsEvent()

    object OnBackClick:BankDetailsEvent()
}