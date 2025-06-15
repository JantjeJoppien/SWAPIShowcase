package dev.joppien.swapishowcase.data.repository

import dev.joppien.swapishowcase.data.local.AppDatabase.Companion.DATABASE_CACHE_VALIDITY
import dev.joppien.swapishowcase.data.local.dao.MovieDao
import dev.joppien.swapishowcase.data.local.entity.MovieEntity
import dev.joppien.swapishowcase.data.mappers.toEntity
import dev.joppien.swapishowcase.data.mappers.toEntityList
import dev.joppien.swapishowcase.data.remote.api.SwapiClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val swapiClient: SwapiClient,
    private val movieDao: MovieDao,
) {

    //region Public API

    /**
     * Gets all movies.
     * Observes changes from the database.
     * Attempts to refresh data from the network when the cached data is null or outdated.
     */
    fun getAllMovies(): Flow<List<MovieEntity>> =
        movieDao.getAllMovies()
            .onStart {
                try {
                    val currentCache = movieDao.getAllMovies().firstOrNull()
                    if (currentCache.isNullOrEmpty()
                        || currentCache.any { it.lastRefreshed < System.currentTimeMillis() - DATABASE_CACHE_VALIDITY }
                    ) {
                        val networkMoviesDto = swapiClient.swapiService.getAllMovies().results
                        movieDao.deleteAllMovies()
                        movieDao.insertAllMovies(networkMoviesDto.toEntityList())
                    }
                } catch (e: Exception) {
                    //ToDo: Handle error
                    println("Network error fetching all movies: ${e.message}")
                }
            }

    /**
     * Gets a specific movie by its ID.
     * Observes changes from the database.
     * Attempts to refresh data from the network when the cached data is null or outdated.
     */
    fun getMovieById(id: Int): Flow<MovieEntity?> =
        movieDao.getMovieById(id)
            .onStart {
                try {
                    val currentCachedMovie = movieDao.getMovieById(id).firstOrNull()
                    if (currentCachedMovie == null
                        || currentCachedMovie.lastRefreshed < System.currentTimeMillis() - DATABASE_CACHE_VALIDITY
                    ) {
                        val networkMovieDto = swapiClient.swapiService.getMovieById(id)
                        val movieEntity = networkMovieDto.toEntity()
                        if (movieEntity != null) {
                            movieDao.insertMovie(movieEntity)
                        }
                    }
                } catch (e: Exception) {
                    //ToDo: Handle error
                    println("Network error fetching movie by ID $id: ${e.message}")
                }
            }

    /**
     * Fetches all movies from the remote API.
     */
    suspend fun refreshAllMoviesFromNetwork() {
        try {
            val networkMoviesDto = swapiClient.swapiService.getAllMovies().results
            movieDao.deleteAllMovies()
            movieDao.insertAllMovies(networkMoviesDto.toEntityList())
        } catch (e: Exception) {
            //ToDo: Handle error
            println("Error refreshing movies from network: ${e.message}")
        }
    }

    //endregion

}
