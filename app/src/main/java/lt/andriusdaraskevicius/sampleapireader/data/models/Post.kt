package lt.andriusdaraskevicius.sampleapireader.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Posts")
data class Post (
        @PrimaryKey
        val id: Long,
        val userId: Long,
        val title: String,
        val body: String
) {
    override fun toString(): String {
        return title
    }
}