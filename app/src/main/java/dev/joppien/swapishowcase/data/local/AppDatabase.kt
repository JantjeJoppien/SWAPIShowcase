package dev.joppien.swapishowcase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.joppien.swapishowcase.data.local.dao.MovieDao

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        const val DATABASE_NAME = "swapi_showcase_db"
    }
}