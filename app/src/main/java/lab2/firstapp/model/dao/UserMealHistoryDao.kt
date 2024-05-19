package lab2.firstapp.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
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

    @Query("SELECT * FROM users_meals_history WHERE user_id = :userId")
    fun getMealHistoryOfUser(userId: Int): Flow<List<UserMealHistory?>>
}