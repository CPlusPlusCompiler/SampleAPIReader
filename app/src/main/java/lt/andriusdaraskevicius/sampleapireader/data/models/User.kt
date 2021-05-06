package lt.andriusdaraskevicius.sampleapireader.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User (
        @PrimaryKey
        val id: Long,
        val name: String,
        val username: String,
        val email: String,
        val phone: String,
        val website: String,
    // todo maybe add company's name
)