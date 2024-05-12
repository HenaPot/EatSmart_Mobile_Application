package lab2.firstapp.model.repositories

import kotlinx.coroutines.flow.Flow
import lab2.firstapp.model.dao.MealDao
import lab2.firstapp.model.models.Meal

class MealRepository(private val mealDao: MealDao): BaseRepository<Meal> {
    override suspend fun insert(t: Meal) = mealDao.insert(t)

    override suspend fun update(t: Meal) = mealDao.update(t)

    override suspend fun delete(t: Meal) = mealDao.delete(t)

    override fun getOneStream(id: Int): Flow<Meal?> = mealDao.getMealById(id)

    fun getAllMeals(): Flow<List<Meal>> = mealDao.getAllMeals()
}