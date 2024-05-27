package lab2.firstapp.viewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import lab2.firstapp.EatSmartApplication

object AppViewModelProvider {


    val Factory = viewModelFactory {
        initializer {
            LoginRegistrationViewModel(
                EatSmartApplication().container.userRepository
            )
        }
        initializer {
            UserViewModel(
                EatSmartApplication().container.userRepository,
                this.createSavedStateHandle()
            )
        }
        initializer {
            MealViewModel(
                EatSmartApplication().container.mealRepository
            )
        }
        initializer {
            AllMealsViewModel(
                EatSmartApplication().container.mealRepository
            )
        }
        initializer {
            UserMealHistoryViewModel(
                EatSmartApplication().container.userMealHistoryRepository
            )
        }

    }
}


fun CreationExtras.EatSmartApplication(): EatSmartApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as EatSmartApplication)