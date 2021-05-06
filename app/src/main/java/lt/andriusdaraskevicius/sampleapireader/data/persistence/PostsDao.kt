package lt.andriusdaraskevicius.sampleapireader.data.persistence

import androidx.room.Dao
import androidx.room.Query
import lt.andriusdaraskevicius.sampleapireader.data.entities.Post

@Dao
interface PostsDao: BaseDao<Post> {

    @Query("select * from Posts")
    suspend fun getAll(): List<Post>

    @Query("select * from Posts where id = :id limit 1")
    suspend fun get(id: Long): Post?

}