package lab2.firstapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import lab2.firstapp.model.models.Meal
import lab2.firstapp.model.repositories.MealRepository
import lab2.firstapp.ui.theme.screen.ProfileDestination

class AllMealsViewModel(private val mealRepository: MealRepository, savedStateHandle: SavedStateHandle): ViewModel() {

    var userId: Int = savedStateHandle[ProfileDestination.userIdArg]!!


    val allMealsUiState: StateFlow<AllMealsUiState> =
        mealRepository.getAllMeals()
            .map {
                AllMealsUiState(it)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = AllMealsUiState()
            )
}

/**
 * Ui State for HomeScreen
 */
data class AllMealsUiState(val mealList: List<Meal> = listOf())