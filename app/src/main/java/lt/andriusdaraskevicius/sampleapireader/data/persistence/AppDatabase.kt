package lt.andriusdaraskevicius.sampleapireader.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import lt.andriusdaraskevicius.sampleapireader.data.entities.Post
import lt.andriusdaraskevicius.sampleapireader.data.entities.User

@Database(entities = [Post::class, User::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun usersDao(): UsersDao
    abstract fun postsDao(): PostsDao

}