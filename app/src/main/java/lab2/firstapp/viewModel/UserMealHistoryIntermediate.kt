package lab2.firstapp.viewModel

import lab2.firstapp.model.models.UserMealHistory
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

import androidx.annotation.DrawableRes
import lab2.firstapp.model.ActivityLevel
import lab2.firstapp.model.DietaryPlan
import lab2.firstapp.model.FitnessPlan
import lab2.firstapp.model.Gender
import lab2.firstapp.model.models.User

// here we give default values
data class UserMealHistoryDetails(
    val id: Int = 0,
    val userId: Int = 1,
    val mealId: Int = 1,
    val timestamp: String = SimpleDateFormat("yyyy-MM-dd").format(Date())
)


data class UserMealHistoryUiState(
    val userMealHistoryDetails: UserMealHistoryDetails = UserMealHistoryDetails()
)


fun UserMealHistoryDetails.toUserMealHistory(): UserMealHistory = UserMealHistory(
    id = id,
    userId = userId,
    mealId = mealId,
    timestamp = timestamp
)


fun UserMealHistory.toUserMealHistoryDetails() = UserMealHistoryDetails(
    id = id,
    userId = userId,
    mealId = mealId,
    timestamp = timestamp

)


fun UserMealHistory.toUserMealHistoryUiState(): UserMealHistoryUiState = UserMealHistoryUiState(
    userMealHistoryDetails = this.toUserMealHistoryDetails()
)
