package lab2.firstapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import lab2.firstapp.model.models.Meal
import lab2.firstapp.model.repositories.MealRepository
import lab2.firstapp.model.repositories.UserMealHistoryRepository

class MealViewModel(private val mealRepository: MealRepository): ViewModel() {
    var mealUiState by mutableStateOf(MealUiState())
        private set

    init {
        viewModelScope.launch {
            mealUiState = mealRepository.getOneStream(1)
                .filterNotNull()
                .first()
                .toMealUiState()
        }
    }

    fun updateUiState(mealDetails: MealDetails) {
        mealUiState = MealUiState(mealDetails)
    }


}