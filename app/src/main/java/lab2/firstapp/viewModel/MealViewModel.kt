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
import lab2.firstapp.model.models.Meal
import lab2.firstapp.model.repositories.MealRepository
import lab2.firstapp.model.repositories.UserMealHistoryRepository
import lab2.firstapp.ui.theme.screen.MealDestination
import lab2.firstapp.ui.theme.screen.ProfileDestination

class MealViewModel(private val mealRepository: MealRepository, savedStateHandle: SavedStateHandle): ViewModel() {
    private val mealId: Int =
        checkNotNull(savedStateHandle[MealDestination.mealId])

    var mealUiState by mutableStateOf(MealUiState())
        private set

    init {
        viewModelScope.launch {
            mealUiState = mealRepository.getOneStream(mealId)
                .filterNotNull()
                .first()
                .toMealUiState()
        }
    }

    fun updateUiState(mealDetails: MealDetails) {
        mealUiState = MealUiState(mealDetails)
    }


}