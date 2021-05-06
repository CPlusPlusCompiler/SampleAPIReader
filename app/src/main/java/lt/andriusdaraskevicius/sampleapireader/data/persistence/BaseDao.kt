package lt.andriusdaraskevicius.sampleapireader.data.persistence

import androidx.room.*

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<T>)

    @Query("delete from Posts")
    suspend fun deleteAll()

    @Update
    suspend fun update(entity: T)
}