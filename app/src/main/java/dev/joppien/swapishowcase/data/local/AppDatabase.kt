package dev.joppien.swapishowcase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.joppien.swapishowcase.data.local.dao.MovieDao
import dev.joppien.swapishowcase.data.local.dao.PersonDao
import dev.joppien.swapishowcase.data.local.dao.PlanetDao
import dev.joppien.swapishowcase.data.local.dao.SpeciesDao
import dev.joppien.swapishowcase.data.local.dao.StarshipDao
import dev.joppien.swapishowcase.data.local.dao.VehicleDao
import dev.joppien.swapishowcase.data.local.entity.MovieEntity
import dev.joppien.swapishowcase.data.local.entity.PersonEntity
import dev.joppien.swapishowcase.data.local.entity.PlanetEntity
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
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    abstract fun personDao(): PersonDao

    abstract fun planetDao(): PlanetDao

    abstract fun speciesDao(): SpeciesDao

    abstract fun starshipDao(): StarshipDao

    abstract fun vehicleDao(): VehicleDao


    companion object {
        const val DATABASE_NAME = "swapi_showcase_db"
        const val DATABASE_CACHE_VALIDITY = 86400000L // 1 day
    }
}