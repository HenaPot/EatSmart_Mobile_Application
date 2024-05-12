package lab2.firstapp.model.models

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import lab2.firstapp.model.ActivityLevel
import lab2.firstapp.model.DietaryPlan
import lab2.firstapp.model.FitnessPlan
import org.jetbrains.annotations.NotNull

@Entity(tableName = "meals")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "mealImage")
    @DrawableRes
    val mealImage: Int,

    @ColumnInfo(name = "calories")
    val calories: Int,

    @ColumnInfo(name = "dietaryPlan")
    val dietaryPlan: DietaryPlan,

    @ColumnInfo(name = "fitnessPlan")
    val fitnessPlan: FitnessPlan,

    @ColumnInfo(name = "activityLevel")
    val activityLevel: ActivityLevel,

    @ColumnInfo(name = "preparationTime")
    val preparationTime: Int,

    @ColumnInfo(name = "ingredientString")
    val ingredientArray: String,

    @ColumnInfo(name = "directionString")
    val directionString: String
)
