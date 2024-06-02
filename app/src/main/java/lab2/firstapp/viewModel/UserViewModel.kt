package lab2.firstapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import lab2.firstapp.model.repositories.UserRepository
import lab2.firstapp.ui.theme.screen.PreferencesDestination
import lab2.firstapp.ui.theme.screen.ProfileDestination

class UserViewModel(private val userRepository: UserRepository, private val savedStateHandle: SavedStateHandle): ViewModel() {
    /*private val userId: Int =
        checkNotNull(savedStateHandle[ProfileDestination.userIdArg])*/

    var userIdArg: Int = savedStateHandle[ProfileDestination.userIdArg]!!

    /*val userId: Int
        get() = savedStateHandle.get<Int>(ProfileDestination.userIdArg) ?: throw IllegalArgumentException("Missing userId")*/



    var userUiState by mutableStateOf(UserUiState())
        private set

    init {
        viewModelScope.launch {
            userUiState = userRepository.getOneStream(userIdArg)
                .filterNotNull()
                .first()
                .toUserUiState(true)
        }
    }
    suspend fun updateUser() {
        userRepository.update(userUiState.userDetails.toUser())
    }

    //FOR LOGOUT
    fun clearUi(){
        userUiState = UserUiState()
    }

    fun updateUiState(userDetails: UserDetails) {
        userUiState =
            UserUiState(userDetails = userDetails, isEntryValid = false)
    }

    fun updateProfilePicture(newProfilePicture: String) {
        val updatedUserDetails = userUiState.userDetails.copy(profilePicture = newProfilePicture)
        userUiState = userUiState.copy(userDetails = updatedUserDetails)
        viewModelScope.launch {
            userRepository.update(updatedUserDetails.toUser())
        }
    }
}