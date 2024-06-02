package lab2.firstapp.model.repositories

import kotlinx.coroutines.flow.Flow
import lab2.firstapp.model.dao.UserMealHistoryDao
import lab2.firstapp.model.models.Meal
import lab2.firstapp.model.models.UserMealHistory

class UserMealHistoryRepository(private val userMealHistoryDao: UserMealHistoryDao): BaseRepository<UserMealHistory> {
    override suspend fun insert(t: UserMealHistory) = userMealHistoryDao.insert(t)

    override suspend fun update(t: UserMealHistory) = userMealHistoryDao.update(t)

    override suspend fun delete(t: UserMealHistory) = userMealHistoryDao.delete(t)

    override fun getOneStream(id: Int): Flow<UserMealHistory?>
        = userMealHistoryDao.getMealHistoryById(id)

    fun getMealHistoryOfUser(userId: Int, date: String): Flow<List<Meal>>
        = userMealHistoryDao.getMealHistoryOfUser(userId, date)

    fun getMealHistoryByMealId(mealId: Int, userId: Int): Flow<UserMealHistory?>
        = userMealHistoryDao.getMealHistoryByMealId(mealId = mealId, userId)

    fun getUsersCaloriesOnDate(userId: Int, date: String): Flow<String?>
        = userMealHistoryDao.getUsersCaloriesOnDate(userId, date)

}