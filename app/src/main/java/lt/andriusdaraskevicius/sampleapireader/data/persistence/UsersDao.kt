package lt.andriusdaraskevicius.sampleapireader.data.persistence

import androidx.room.Dao
import androidx.room.Query
import lt.andriusdaraskevicius.sampleapireader.data.entities.User

@Dao
interface UsersDao: BaseDao<User> {

   @Query("select * from Users")
   suspend fun getAll(): List<User>

   @Query("select * from Users where id = :id limit 1")
   suspend fun get(id: Long): User?
}