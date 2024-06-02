package lab2.firstapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.first
import lab2.firstapp.model.repositories.UserRepository

class LoginRegistrationViewModel(private val userRepository: UserRepository): ViewModel() {
    /**
     * Holds current item ui state
     */
    var userUiState by mutableStateOf(UserUiState())
        private set


    fun updateUiState(userDetails: UserDetails) {
        userUiState =
            UserUiState(userDetails = userDetails, isEntryValid = false)
    }

    suspend fun login(): Boolean {
        val res = userRepository.login(userUiState.userDetails.email, userUiState.userDetails.password)
            .first()
            ?.toUserUiState(true)

        if(res != null){
            userUiState = res
            return true
        }else{
            return false
        }
    }

    /*suspend fun register(): Boolean{
        if(validateInput()){
            userRepository.insert(userUiState.userDetails.toUser())
            login()
            return true
        }else return false
    }*/

    suspend fun register(): Boolean{
        if(userRepository.getUserByEmail(userUiState.userDetails.email).first()?.email != null) {
            return false;
        }
        if(!login() && userUiState.userDetails.email != "" && userUiState.userDetails.password.trim() != ""){
            userRepository.insert(userUiState.userDetails.toUser())
            login()
            return true
        }else return false
    }

    private suspend fun validateInput(uiState: UserDetails = userUiState.userDetails): Boolean {
        return with(uiState) {
            checkEmail()
        }
    }

    private suspend fun checkEmail(): Boolean{
        return userRepository.getUserByEmail(userUiState.userDetails.email).first()
            ?.toUserUiState()?.userDetails?.email?.isEmpty() ?: true
    }

}