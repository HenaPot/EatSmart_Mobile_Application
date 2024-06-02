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
import lab2.firstapp.R
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

    init {
        // Launch a coroutine to perform the initialization
        viewModelScope.launch {
            // Access the meal list from the repository
            val mealList = allMealsUiState.value.mealList

            // Initialize an array of meal image resource IDs
            val mealImageResources = intArrayOf(
                R.drawable.meal0,
                R.drawable.meal1,
                R.drawable.meal2,
                // Add more meal image resource IDs here as needed
            )

            // Iterate over each meal and update the mealImage
            mealList.forEachIndexed { index, meal ->
                // Ensure the counter doesn't exceed the number of meal image resources
                val mealImageIndex = index % mealImageResources.size

                // Get the updated meal image resource ID
                val updatedMealImage = mealImageResources[mealImageIndex]

                // Update the meal image for the current meal
                updateMealImage(meal.id, updatedMealImage)
            }
        }
    }


    fun updateMealImage(mealId: Int, mealImage: Int) {
        viewModelScope.launch {
            mealRepository.updateMealImage(mealId, mealImage)
        }
    }
}

/**
 * Ui State for HomeScreen
 */
data class AllMealsUiState(val mealList: List<Meal> = listOf())