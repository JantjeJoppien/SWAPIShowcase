package dev.joppien.swapishowcase.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.joppien.swapishowcase.data.local.AppDatabase
import dev.joppien.swapishowcase.data.local.dao.MovieDao
import dev.joppien.swapishowcase.data.local.dao.PersonDao
import dev.joppien.swapishowcase.data.local.dao.PlanetDao
import dev.joppien.swapishowcase.data.local.dao.SpeciesDao
import dev.joppien.swapishowcase.data.local.dao.StarshipDao
import dev.joppien.swapishowcase.data.local.dao.VehicleDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.Companion.DATABASE_NAME
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao()
    }

    @Provides
    @Singleton
    fun providePersonDao(appDatabase: AppDatabase): PersonDao {
        return appDatabase.personDao()
    }

    @Provides
    @Singleton
    fun providePlanetDao(appDatabase: AppDatabase): PlanetDao {
        return appDatabase.planetDao()
    }

    @Provides
    @Singleton
    fun provideSpeciesDao(appDatabase: AppDatabase): SpeciesDao {
        return appDatabase.speciesDao()
    }

    @Provides
    @Singleton
    fun provideStarshipDao(appDatabase: AppDatabase): StarshipDao {
        return appDatabase.starshipDao()
    }

    @Provides
    @Singleton
    fun provideVehicleDao(appDatabase: AppDatabase): VehicleDao {
        return appDatabase.vehicleDao()
    }

}