package lab2.firstapp.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import lab2.firstapp.model.models.Meal
import lab2.firstapp.model.models.UserMealHistory
import lab2.firstapp.model.repositories.UserMealHistoryRepository
import lab2.firstapp.ui.theme.screen.getTodayDateString

class UserMealHistoryViewModel(private val userMealHistoryRepository: UserMealHistoryRepository): ViewModel() {

    private val _userMealHistoryUiState = MutableStateFlow(MealHistoryUiState())
    val userMealHistoryUiState: StateFlow<MealHistoryUiState> = _userMealHistoryUiState

    private val _totalCalories = MutableStateFlow<String>("0")
    val totalCalories: StateFlow<String> = _totalCalories

    init {
        viewModelScope.launch {
            // THIS WILL HAVE TO BE EDITED AS VALUES ARE HARDCODED BELOW
            getUserHistory(2, getTodayDateString())
        }
    }

    fun updateUiState(mealHistoryList: List<Meal>) {
        _userMealHistoryUiState.value = MealHistoryUiState(mealHistoryList)
    }

    suspend fun deleteMealHistory(mealId: Int) {
        val userMealHistoryFlow = userMealHistoryRepository.getMealHistoryByMealId(mealId)
        val userMealHistory = userMealHistoryFlow.firstOrNull()

        userMealHistory?.let {
            userMealHistoryRepository.delete(it)
        }
        /*viewModelScope.launch {
            val deleteMe = userMealHistoryRepository
                .getMealHistoryByMealId(meal.id)
                .first()
            userMealHistoryRepository.delete(deleteMe)
        }*/
    }

    /*suspend fun updateMealHistory(id: Int, userId: Int, mealId: Int, timestamp: String) {
        userMealHistoryRepository.update(UserMealHistory(id, userId, mealId, timestamp))
    }*/

     suspend fun getUsersCaloriesOnDate(userId: Int, date: String) {
        viewModelScope.launch {
            userMealHistoryRepository.getUsersCaloriesOnDate(userId, date)
                .collect { calories ->
                    if (calories != null) {
                        _totalCalories.value = calories
                    } else {
                        _totalCalories.value = "0"
                    }
                }
        }
    }

    suspend fun getUserHistory(userId: Int, date: String) {
         viewModelScope.launch {
             userMealHistoryRepository.getMealHistoryOfUser(userId, date)
                 .map { MealHistoryUiState(it) }
                 .stateIn(
                     scope = viewModelScope,
                     started = SharingStarted.WhileSubscribed(5000L),
                     initialValue = MealHistoryUiState()
                 ).collect {
                     _userMealHistoryUiState.value = it
                 }
         }
    }

}
data class MealHistoryUiState(val mealHistoryList: List<Meal> = listOf())

