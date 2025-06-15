package dev.joppien.swapishowcase.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.joppien.swapishowcase.data.local.entity.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertPerson(person: PersonEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAllPeople(people: List<PersonEntity>)

    @Query("SELECT * FROM people WHERE id = :personId")
    fun getPersonById(personId: Int): Flow<PersonEntity?>

    @Query("SELECT * FROM people ORDER BY name ASC")
    fun getAllPeople(): Flow<List<PersonEntity>>

    @Query("DELETE FROM people")
    suspend fun deleteAllPeople()
}