package lab2.firstapp.model.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.sql.Timestamp

@Entity(tableName = "users_meals_history")
data class UserMealHistory(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "user_id")
    val userId: Int,

    @ColumnInfo(name = "meal_id")
    val mealId: Int,

    @ColumnInfo(name = "timestamp")
    val timestamp: String
)
