package dev.joppien.swapishowcase.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.joppien.swapishowcase.data.local.entity.SpeciesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SpeciesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecies(species: SpeciesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSpecies(speciesList: List<SpeciesEntity>)

    @Query("SELECT * FROM species_table WHERE id = :speciesId")
    fun getSpeciesById(speciesId: Int): Flow<SpeciesEntity?>

    @Query("SELECT * FROM species_table ORDER BY name ASC")
    fun getAllSpecies(): Flow<List<SpeciesEntity>>

    @Query("DELETE FROM species_table")
    suspend fun deleteAllSpecies()
}