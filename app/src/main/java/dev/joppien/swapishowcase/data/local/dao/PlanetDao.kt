package dev.joppien.swapishowcase.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.joppien.swapishowcase.data.local.entity.PlanetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanetDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertPlanet(planet: PlanetEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAllPlanets(planets: List<PlanetEntity>)

    @Query("SELECT * FROM planets WHERE id = :planetId")
    fun getPlanetById(planetId: Int): Flow<PlanetEntity?>

    @Query("SELECT * FROM planets ORDER BY name ASC")
    fun getAllPlanets(): Flow<List<PlanetEntity>>

    @Query("DELETE FROM planets")
    suspend fun deleteAllPlanets()
}