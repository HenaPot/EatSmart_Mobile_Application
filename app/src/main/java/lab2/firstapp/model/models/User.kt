package lab2.firstapp.model.models

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import lab2.firstapp.model.ActivityLevel
import lab2.firstapp.model.DietaryPlan
import lab2.firstapp.model.FitnessPlan
import lab2.firstapp.model.Gender
import org.jetbrains.annotations.NotNull

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "surname")
    val surname: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "weight")
    val weight: Double,

    @ColumnInfo(name = "height")
    val height: Double,

    @ColumnInfo(name = "dietaryPlan")
    val dietaryPlan: DietaryPlan,

    @ColumnInfo(name = "fitnessPlan")
    val fitnessPlan: FitnessPlan,

    @ColumnInfo(name = "activityLevel")
    val activityLevel: ActivityLevel,

    @ColumnInfo(name = "gender")
    val gender: Gender,

    @ColumnInfo(name = "dateOfBirth")
    val dateOfBirth: String,

    @ColumnInfo(name = "profilePicture")
    @DrawableRes
    val profilePicture: Int,

    @ColumnInfo(name = "calories")
    val calories: Int

)
