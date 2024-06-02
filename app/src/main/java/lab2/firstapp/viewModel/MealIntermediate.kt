package lab2.firstapp.viewModel

import androidx.annotation.DrawableRes
import lab2.firstapp.R
import lab2.firstapp.model.ActivityLevel
import lab2.firstapp.model.DietaryPlan
import lab2.firstapp.model.FitnessPlan
import lab2.firstapp.model.models.Meal

data class MealDetails(
    val id: Int = 0,
    val name: String = "Meal",
    val description: String = "This is description of a meal",
    @DrawableRes
    val mealImage: Int = R.drawable.meal0,
    val calories: Int = 450,
    val dietaryPlan: DietaryPlan = DietaryPlan.OMNIVORE,
    val fitnessPlan: FitnessPlan = FitnessPlan.MAINTAIN_WEIGHT,
    val activityLevel: ActivityLevel = ActivityLevel.LIGHT_ACTIVE,
    val preparationTime: Int = 30,
    val ingredientArray: String = "",
    val directionString: String = ""
)

data class MealUiState(
    val mealDetails: MealDetails = MealDetails()
)

fun MealDetails.toMeal() : Meal = Meal(
    id = id,
    name = name,
    description = description,
    mealImage = mealImage,
    calories = calories,
    dietaryPlan = dietaryPlan,
    fitnessPlan = fitnessPlan,
    activityLevel = activityLevel,
    preparationTime = preparationTime,
    ingredientArray = ingredientArray,
    directionString = directionString
)

fun Meal.toMealDetails() = MealDetails(
    id = id,
    name = name,
    description = description,
    mealImage = mealImage,
    calories = calories,
    dietaryPlan = dietaryPlan,
    fitnessPlan = fitnessPlan,
    activityLevel = activityLevel,
    preparationTime = preparationTime,
    ingredientArray = ingredientArray,
    directionString = directionString
)

fun Meal.toMealUiState(): MealUiState = MealUiState(
    mealDetails = this.toMealDetails()
)