package lt.andriusdaraskevicius.sampleapireader.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import lt.andriusdaraskevicius.sampleapireader.data.models.Post
import lt.andriusdaraskevicius.sampleapireader.data.models.User

@Database(entities = [Post::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun postsDao(): PostsDao

}