package lab2.firstapp.model

import android.content.Context
import lab2.firstapp.model.repositories.MealRepository
import lab2.firstapp.model.repositories.UserMealHistoryRepository
import lab2.firstapp.model.repositories.UserRepository

interface AppContainer {
    val userRepository: UserRepository
    val mealRepository: MealRepository
    val userMealHistoryRepository: UserMealHistoryRepository
}

class AppDataContainer(private val context: Context): AppContainer{
    override val userRepository: UserRepository by lazy {
        UserRepository(EatSmartDatabase.getDatabase(context).userDao())
    }

    override val mealRepository: MealRepository by lazy {
        MealRepository(EatSmartDatabase.getDatabase(context).mealDao())
    }

    override val userMealHistoryRepository: UserMealHistoryRepository by lazy {
        UserMealHistoryRepository(EatSmartDatabase.getDatabase(context).userMealHistoryDao())
    }
}