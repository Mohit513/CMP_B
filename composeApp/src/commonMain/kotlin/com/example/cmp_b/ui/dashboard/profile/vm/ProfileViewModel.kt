package com.example.cmp_b.ui.dashboard.profile.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmp_b.core.data.session.SessionManager
import com.example.cmp_b.navigation.NavRoutes
import com.example.cmp_b.navigation.NavigationEvent
import com.example.cmp_b.util.ProfileDataSource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val sessionManager: SessionManager
) : ViewModel()
{

    private val _uiState = MutableStateFlow(ProfileUiState(isLoading = true))
    val uiState: StateFlow<ProfileUiState> = _uiState

    private val _editUiState = MutableStateFlow(EditProfileUiState())
    val editUiState: StateFlow<EditProfileUiState> = _editUiState

    private val _uiEvent = Channel<String>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _navigation = Channel<NavigationEvent>()
    val navigation = _navigation.receiveAsFlow()

    private var dataSource: ProfileDataSource = ProfileDataSource.DUMMY
    private var hasLoadedProfile = false

    init {
        loadProfile()
    }

    private fun sendNavigation(event: NavigationEvent) {
        viewModelScope.launch { _navigation.send(event) }
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.OnBackClick -> sendNavigation(NavigationEvent.PopBack)
            ProfileEvent.OnLogoutClick -> logout()
            ProfileEvent.OnDeleteClick -> deleteUser()
            ProfileEvent.OnEditClick -> openEditProfile()
            ProfileEvent.LoadProfile -> loadProfile()
        }
    }

    private fun openEditProfile() {
        viewModelScope.launch {
            _uiState.update { it.copy(isEditLoading = true) }
            
            // Prefill edit state
            setPrefilledData(_uiState.value)
            
            delay(800)
            sendNavigation(NavigationEvent.Navigate(NavRoutes.EditProfileScreen.route))
            _uiState.update { it.copy(isEditLoading = false) }
        }
    }

    private fun setPrefilledData(profile: ProfileUiState) {
        _editUiState.value = EditProfileUiState(
            profileImage = profile.profileImage,
            mobileNumber = profile.mobile,
            email = profile.email,
            aadharNumber = profile.aadhar.filter { it.isDigit() },
            dob = profile.dob,
            maritalStatus = profile.maritalStatus,
            bloodGroup = profile.bloodGroup,
            clientName = profile.clientName,
            reportingManager = profile.reportingManager,
            innovId = profile.innovId,
            employeeId = profile.employeeCode,
            uanNumber = profile.uan,
            rfNumber = profile.rf,
            city = profile.city,
            state = profile.state,
            address = profile.address
        )
    }

    fun onEditEvent(event: EditProfileEvent) {
        when (event) {
            is EditProfileEvent.OnProfileImageChanged -> {
                _editUiState.update { it.copy(profileImage = event.value) }
            }
            EditProfileEvent.OnBackClick -> sendNavigation(NavigationEvent.PopBack)
            is EditProfileEvent.OnMobileChanged -> {
                if (!event.value.all { it.isDigit() }) return
                if (event.value.length > 10) return
                _editUiState.update { it.copy(mobileNumber = event.value) }
            }
            is EditProfileEvent.OnEmailChanged -> {
                if (event.value.length > 50) return
                _editUiState.update { it.copy(email = event.value) }
            }
            is EditProfileEvent.OnAadharChanged -> {
                val digits = event.value.filter { it.isDigit() }.take(12)
                _editUiState.update { it.copy(aadharNumber = digits) }
            }
            is EditProfileEvent.OnDobSelected -> _editUiState.update { it.copy(dob = event.value) }
            is EditProfileEvent.OnMaritalStatusChanged -> _editUiState.update { it.copy(maritalStatus = event.value) }
            is EditProfileEvent.OnBloodGroupChanged -> _editUiState.update { it.copy(bloodGroup = event.value) }
            is EditProfileEvent.OnClientNameChanged -> _editUiState.update { it.copy(clientName = event.value) }
            is EditProfileEvent.OnReportingManagerChanged -> _editUiState.update { it.copy(reportingManager = event.value) }
            is EditProfileEvent.OnInnovIdChanged -> _editUiState.update { it.copy(innovId = event.value) }
            is EditProfileEvent.OnEmployeeIdChanged -> _editUiState.update { it.copy(employeeId = event.value) }
            is EditProfileEvent.OnUanChanged -> _editUiState.update { it.copy(uanNumber = event.value) }
            is EditProfileEvent.OnRfChanged -> _editUiState.update { it.copy(rfNumber = event.value) }
            is EditProfileEvent.OnCityChanged -> _editUiState.update { it.copy(city = event.value) }
            is EditProfileEvent.OnStateChanged -> _editUiState.update { it.copy(state = event.value) }
            is EditProfileEvent.OnAddressChanged -> _editUiState.update { it.copy(address = event.value) }
            EditProfileEvent.OnSaveClick -> {
                if (validateEditFields()) {
                    updateProfile(_editUiState.value)
                    sendNavigation(NavigationEvent.PopBack)
                }
            }
        }
    }

    private fun validateEditFields(): Boolean {
        val state = _editUiState.value
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,}$".toRegex()

        return when {
            state.mobileNumber.length != 10 || state.mobileNumber.firstOrNull() !in '6'..'9' -> {
                showSnackBar("Enter valid 10-digit mobile number starting with 6-9")
                false
            }
            !emailRegex.matches(state.email) -> {
                showSnackBar("Enter valid email")
                false
            }
            state.aadharNumber.length != 12 -> {
                showSnackBar("Enter valid 12-digit Aadhar number")
                false
            }
            state.dob.isBlank() -> { showSnackBar("Select date of birth"); false }
            state.maritalStatus.isBlank() -> { showSnackBar("Select marital status"); false }
            state.bloodGroup.isBlank() -> { showSnackBar("Select blood group"); false }
            state.clientName.isBlank() -> { showSnackBar("Enter client name"); false }
            state.reportingManager.isBlank() -> { showSnackBar("Enter reporting manager"); false }
            state.employeeId.isBlank() -> { showSnackBar("Enter employee ID"); false }
            state.city.isBlank() -> { showSnackBar("Select city"); false }
            state.state.isBlank() -> { showSnackBar("Select state"); false }
            state.address.isBlank() -> { showSnackBar("Enter address"); false }
            else -> true
        }
    }

    private fun showSnackBar(message: String) {
        viewModelScope.launch { _uiEvent.send(message) }
    }

    fun loadProfile() {

        if (hasLoadedProfile) return

        viewModelScope.launch {

            hasLoadedProfile = true
            delay(500)

            val state = ProfileUiState(
                isLoading = false,
                userName = "Shubham Joshi",
                employeeId = "3447687",
                mobile = "7089678924",
                email = "lorem@ipsum.com",
                aadhar = "123456789012",
                dob = "01-Jan-1990",
                gender = "Male",
                maritalStatus = "Married",
                bloodGroup = "O+",
                clientName = "Client A",
                reportingManager = "Manager B",
                innovId = "INN-123",
                employeeCode = "3447687",
                uan = "78263987323",
                rf = "872463895723",
                city = "Bhopal",
                state = "MP",
                address = "MP Nagar Bhopal"
            )

            _uiState.value = buildUiState(state)
        }
    }

    fun updateProfile(updated: EditProfileUiState) {

        dataSource = ProfileDataSource.EDITED

        val current = _uiState.value

        val newState = current.copy(
            mobile = updated.mobileNumber,
            email = updated.email,
            aadhar = updated.aadharNumber,
            dob = updated.dob,
            maritalStatus = updated.maritalStatus,
            bloodGroup = updated.bloodGroup,
            clientName = updated.clientName,
            reportingManager = updated.reportingManager,
            innovId = updated.innovId,
            employeeCode = updated.employeeId,
            uan = updated.uanNumber,
            rf = updated.rfNumber,
            city = updated.city,
            state = updated.state,
            address = updated.address,
            profileImage = updated.profileImage
        )

        _uiState.value = buildUiState(newState)
    }

    private fun logout() {
        viewModelScope.launch {
            sessionManager.clearSession()
            sendNavigation(NavigationEvent.ClearBackStackAndNavigate(NavRoutes.Login.route))
        }
    }

    private fun deleteUser() {}

    fun maskAadhar(aadhar: String): String {
        return if (aadhar.length >= 12) {
            "XXXX-XXXX-" + aadhar.takeLast(4)
        } else aadhar
    }

    private fun buildUiState(state: ProfileUiState): ProfileUiState {
        return state.copy(
            personalDetails = listOf(
                KeyValue("Mobile Number", state.mobile),
                KeyValue("Email ID", state.email),
                KeyValue("Aadhar Number", maskAadhar(state.aadhar)),
                KeyValue("Date Of Birth", state.dob),
                KeyValue("Gender", state.gender),
                KeyValue("Marital Status", state.maritalStatus),
                KeyValue("Blood Group", state.bloodGroup)
            ),
            employmentDetails = listOf(
                KeyValue("Client Name", state.clientName),
                KeyValue("Reporting Manager", state.reportingManager),
                KeyValue("Innov ID", state.innovId),
                KeyValue("Employee ID", state.employeeCode),
                KeyValue("UAN Number", state.uan),
                KeyValue("RF Number", state.rf)
            ),
            addressDetails = listOf(
                KeyValue("City", state.city),
                KeyValue("State", state.state),
                KeyValue("Address", state.address)
            )
        )
    }
}

data class ProfileUiState(
    val profileImage: String = "",
    val isLoading: Boolean = false,
    val isEditLoading: Boolean = false,
    val userName: String = "",
    val employeeId: String = "",
    val profileImageUrl: String? = null,
    val mobile: String = "",
    val email: String = "",
    val aadhar: String = "",
    val dob: String = "",
    val gender: String = "",
    val maritalStatus: String = "",
    val bloodGroup: String = "",
    val clientName: String = "",
    val reportingManager: String = "",
    val innovId: String = "",
    val employeeCode: String = "",
    val uan: String = "",
    val rf: String = "",
    val city: String = "",
    val state: String = "",
    val address: String = "",
    val personalDetails: List<KeyValue> = emptyList(),
    val employmentDetails: List<KeyValue> = emptyList(),
    val addressDetails: List<KeyValue> = emptyList(),
    val error: String? = null
)

data class EditProfileUiState(
    val profileImage: String = "",
    val mobileNumber: String = "",
    val email: String = "",
    val aadharNumber: String = "",
    val dob: String = "",
    val maritalStatus: String = "",
    val bloodGroup: String = "",
    val clientName: String = "",
    val reportingManager: String = "",
    val employeeId: String = "",
    val city: String = "",
    val state: String = "",
    val address: String = "",
    val innovId: String = "",
    val uanNumber: String = "",
    val rfNumber: String = "",
    val isLoading: Boolean = false
)

data class KeyValue(
    val key: String,
    val value: String
)

sealed class ProfileEvent {
    data object OnBackClick : ProfileEvent()
    data object OnEditClick : ProfileEvent()
    data object OnDeleteClick : ProfileEvent()
    data object OnLogoutClick : ProfileEvent()
    data object LoadProfile : ProfileEvent()
}

sealed class EditProfileEvent {
    data class OnProfileImageChanged(val value: String) : EditProfileEvent()
    data class OnMobileChanged(val value: String) : EditProfileEvent()
    data class OnEmailChanged(val value: String) : EditProfileEvent()
    data class OnAadharChanged(val value: String) : EditProfileEvent()
    data class OnDobSelected(val value: String) : EditProfileEvent()
    data class OnMaritalStatusChanged(val value: String) : EditProfileEvent()
    data class OnBloodGroupChanged(val value: String) : EditProfileEvent()
    data class OnClientNameChanged(val value: String) : EditProfileEvent()
    data class OnReportingManagerChanged(val value: String) : EditProfileEvent()
    data class OnInnovIdChanged(val value: String) : EditProfileEvent()
    data class OnEmployeeIdChanged(val value: String) : EditProfileEvent()
    data class OnUanChanged(val value: String) : EditProfileEvent()
    data class OnRfChanged(val value: String) : EditProfileEvent()
    data class OnCityChanged(val value: String) : EditProfileEvent()
    data class OnStateChanged(val value: String) : EditProfileEvent()
    data class OnAddressChanged(val value: String) : EditProfileEvent()
    data object OnSaveClick : EditProfileEvent()
    data object OnBackClick : EditProfileEvent()
}
