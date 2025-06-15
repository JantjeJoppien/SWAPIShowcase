package dev.joppien.swapishowcase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.joppien.swapishowcase.data.local.dao.MovieDao
import dev.joppien.swapishowcase.data.local.entity.MovieEntity
import dev.joppien.swapishowcase.data.local.entity.PlanetEntity
import dev.joppien.swapishowcase.data.local.entity.PersonEntity
import dev.joppien.swapishowcase.data.local.entity.SpeciesEntity
import dev.joppien.swapishowcase.data.local.entity.StarshipEntity
import dev.joppien.swapishowcase.data.local.entity.VehicleEntity

@Database(
    entities = [
        MovieEntity::class,
        PlanetEntity::class,
        PersonEntity::class,
        StarshipEntity::class,
        VehicleEntity::class,
        SpeciesEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        const val DATABASE_NAME = "swapi_showcase_db"
    }
}