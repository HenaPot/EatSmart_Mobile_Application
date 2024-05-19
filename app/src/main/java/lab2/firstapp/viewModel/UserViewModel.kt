package lab2.firstapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import lab2.firstapp.model.repositories.UserRepository

class UserViewModel(private val userRepository: UserRepository): ViewModel() {
    var userUiState by mutableStateOf(UserUiState())
        private set

    init {
        viewModelScope.launch {
            userUiState = userRepository.getOneStream(2)
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
}