package lab2.firstapp.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import lab2.firstapp.model.models.Meal
import lab2.firstapp.model.models.UserMealHistory

@Dao
interface UserMealHistoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userMealHistory: UserMealHistory)

    @Update
    suspend fun update(userMealHistory: UserMealHistory)

    @Delete
    suspend fun delete(userMealHistory: UserMealHistory)

    @Query("SELECT * FROM users_meals_history WHERE id = :id")
    fun getMealHistoryById(id: Int): Flow<UserMealHistory?>

    /*@Query("SELECT * FROM users_meals_history WHERE user_id = :userId")
    fun getMealHistoryOfUser(userId: Int): Flow<List<UserMealHistory?>>*/

    @Query("SELECT meals.* FROM meals " +
            "JOIN users_meals_history ON users_meals_history.meal_id = meals.id " +
            "JOIN users ON users_meals_history.user_id = users.id " +
            "WHERE users.id = :userId AND users_meals_history.timestamp = :date")
    fun getMealHistoryOfUser(userId: Int, date: String): Flow<List<Meal>>

    @Query("SELECT users_meals_history.* FROM users_meals_history " +
            "JOIN meals ON users_meals_history.meal_id = meals.id AND users_meals_history.user_id = :userId " +
            "WHERE meals.id = :mealId")
    fun getMealHistoryByMealId(mealId: Int, userId: Int): Flow<UserMealHistory?>

    @Query("SELECT SUM(meals.calories) as total_calories" +
            " FROM meals " +
            "JOIN users_meals_history ON meals.id = users_meals_history.meal_id " +
            "WHERE users_meals_history.user_id = :userId AND users_meals_history.timestamp = :date")
    fun getUsersCaloriesOnDate(userId: Int, date: String): Flow<String?>

}