package lab2.firstapp.model.repositories

import kotlinx.coroutines.flow.Flow
import lab2.firstapp.model.dao.UserDao
import lab2.firstapp.model.models.User

class UserRepository(private val userDao: UserDao): BaseRepository<User> {
    override suspend fun insert(t: User) = userDao.insert(t)

    override suspend fun update(t: User) = userDao.update(t)

    override suspend fun delete(t: User) = userDao.delete(t)

    override fun getOneStream(id: Int): Flow<User?> = userDao.getUserById(id)

    fun getUserByEmail(email: String): Flow<User?> = userDao.getUserByEmail(email)

    fun login(email: String, password: String): Flow<User?> = userDao.login(email, password)
}