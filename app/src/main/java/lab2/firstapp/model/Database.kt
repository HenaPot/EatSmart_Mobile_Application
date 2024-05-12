package lab2.firstapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import lab2.firstapp.model.dao.MealDao
import lab2.firstapp.model.dao.UserDao
import lab2.firstapp.model.dao.UserMealHistoryDao
import lab2.firstapp.model.models.Meal
import lab2.firstapp.model.models.User
import lab2.firstapp.model.models.UserMealHistory

@Database(entities = [User::class, Meal::class, UserMealHistory::class], version = 1, exportSchema = false)
abstract class EatSmartDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun mealDao(): MealDao
    abstract fun userMealHistoryDao(): UserMealHistoryDao


    companion object{
        @Volatile
        private var Instance: EatSmartDatabase? = null

        fun getDatabase(context: Context): EatSmartDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, EatSmartDatabase::class.java, "EatSmartDatabase")
                    .build().also { Instance = it }
            }
        }
    }
}
