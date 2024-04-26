package lab2.firstapp.model

import androidx.annotation.DrawableRes

data class Meal(
    val id: Int,
    val name: String,
    val description: String,
    @DrawableRes val image: Int,
    val calories: Int,
    val dietaryPlan: DietaryPlan,
    val fitnessPlan: FitnessPlan,
    val activityLevel: ActivityLevel,
    val preparationTime: Int,
    val ingredientArray: Array<String>,
    val directionsArray: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Meal

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (image != other.image) return false
        if (calories != other.calories) return false
        if (dietaryPlan != other.dietaryPlan) return false
        if (fitnessPlan != other.fitnessPlan) return false
        if (activityLevel != other.activityLevel) return false
        if (preparationTime != other.preparationTime) return false
        if (!ingredientArray.contentEquals(other.ingredientArray)) return false
        if (!directionsArray.contentEquals(other.directionsArray)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + image
        result = 31 * result + calories
        result = 31 * result + dietaryPlan.hashCode()
        result = 31 * result + fitnessPlan.hashCode()
        result = 31 * result + activityLevel.hashCode()
        result = 31 * result + preparationTime
        result = 31 * result + ingredientArray.contentHashCode()
        result = 31 * result + directionsArray.contentHashCode()
        return result
    }
}