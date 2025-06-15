package dev.joppien.swapishowcase.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.joppien.swapishowcase.data.local.entity.StarshipEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StarshipDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStarship(starship: StarshipEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllStarships(starships: List<StarshipEntity>)

    @Query("SELECT * FROM starships WHERE id = :starshipId")
    fun getStarshipById(starshipId: Int): Flow<StarshipEntity?>

    @Query("SELECT * FROM starships ORDER BY name ASC")
    fun getAllStarships(): Flow<List<StarshipEntity>>

    @Query("DELETE FROM starships")
    suspend fun deleteAllStarships()
}