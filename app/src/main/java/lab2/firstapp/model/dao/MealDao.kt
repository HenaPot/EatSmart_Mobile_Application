package lab2.firstapp.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import lab2.firstapp.model.models.Meal

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(meal: Meal)

    @Update
    suspend fun update(meal: Meal)

    @Delete
    suspend fun delete(meal: Meal)

    @Query("SELECT * FROM meals WHERE id = :id")
    fun getMealById(id: Int): Flow<Meal>

    @Query("SELECT * FROM meals")
    fun getAllMeals(): Flow<List<Meal>>

    @Query("UPDATE meals SET mealImage = :mealImage WHERE id = :mealId ")
    suspend fun updateMealImage(mealId: Int, mealImage: Int)
}