package lab2.firstapp.viewModel

import androidx.annotation.DrawableRes
import lab2.firstapp.model.ActivityLevel
import lab2.firstapp.model.DietaryPlan
import lab2.firstapp.model.FitnessPlan
import lab2.firstapp.model.Gender
import lab2.firstapp.model.models.User

// here we give default values
data class UserDetails(
    val id: Int = 0,
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = "",
    val weight: Double = 70.00,
    val height: Double = 170.00,
    val dietaryPlan: DietaryPlan = DietaryPlan.OMNIVORE,
    val fitnessPlan: FitnessPlan = FitnessPlan.MAINTAIN_WEIGHT,
    val activityLevel: ActivityLevel = ActivityLevel.LIGHT_ACTIVE,
    val gender: Gender = Gender.FEMALE,
    val dateOfBirth: String = "1999-05-15",
    // ovo je za sliku
    @DrawableRes
    val profilePicture: Int = 0,
    val calories: Int = 2000
)


data class UserUiState(
    val userDetails: UserDetails = UserDetails(),
    val isEntryValid: Boolean = false
)


fun UserDetails.toUser(): User = User(
    id = id,
    name = name,
    surname = surname,
    email = email,
    password = password,
    weight = weight,
    height = height,
    dietaryPlan = dietaryPlan,
    fitnessPlan = fitnessPlan,
    activityLevel = activityLevel,
    gender = gender,
    dateOfBirth = dateOfBirth,
    profilePicture = profilePicture,
    calories = calories
)


fun User.toUserDetails() = UserDetails(
    id = id,
    name = name,
    surname = surname,
    email = email,
    password = password,
    weight = weight,
    height = height,
    dietaryPlan = dietaryPlan ?: DietaryPlan.OMNIVORE,
    fitnessPlan = fitnessPlan ?: FitnessPlan.MAINTAIN_WEIGHT,
    activityLevel = activityLevel ?: ActivityLevel.LIGHT_ACTIVE,
    gender = gender,
    dateOfBirth = dateOfBirth ?: "",
    profilePicture = profilePicture,
    calories = calories
)


fun User.toUserUiState(isEntryValid: Boolean = false): UserUiState = UserUiState(
    userDetails = this.toUserDetails(),
    isEntryValid = isEntryValid
)
