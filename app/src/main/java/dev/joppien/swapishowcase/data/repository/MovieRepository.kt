package dev.joppien.swapishowcase.data.repository

import dev.joppien.swapishowcase.data.local.AppDatabase.Companion.DATABASE_CACHE_VALIDITY
import dev.joppien.swapishowcase.data.local.dao.MovieDao
import dev.joppien.swapishowcase.data.mappers.toDomain
import dev.joppien.swapishowcase.data.mappers.toEntity
import dev.joppien.swapishowcase.data.mappers.toEntityList
import dev.joppien.swapishowcase.data.remote.api.SwapiService
import dev.joppien.swapishowcase.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val swapiService: SwapiService,
    private val movieDao: MovieDao,
) {

    //region Public API

    /**
     * Gets all movies.
     * Observes changes from the database.
     * Attempts to refresh data from the network when the cached data is null or outdated.
     */
    fun getAllMovies(): Flow<List<Movie>> =
        movieDao.getAllMovies()
            .onStart {
                try {
                    val currentCache = movieDao.getAllMovies().firstOrNull()
                    if (currentCache.isNullOrEmpty()
                        || currentCache.any { it.lastRefreshed < System.currentTimeMillis() - DATABASE_CACHE_VALIDITY }
                    ) {
                        val networkMoviesDto = swapiService.getAllMovies().results
                        movieDao.deleteAllMovies()
                        movieDao.insertAllMovies(networkMoviesDto.toEntityList())
                    }
                } catch (e: Exception) {
                    Timber.e(e, "Network error fetching all movies")
                }
            }.map { it.toDomain() }

    /**
     * Gets a specific movie by its ID.
     * Observes changes from the database.
     * Attempts to refresh data from the network when the cached data is null or outdated.
     */
    fun getMovieById(id: Int): Flow<Movie?> =
        movieDao.getMovieById(id)
            .onStart {
                try {
                    val currentCachedMovie = movieDao.getMovieById(id).firstOrNull()
                    if (currentCachedMovie == null
                        || currentCachedMovie.lastRefreshed < System.currentTimeMillis() - DATABASE_CACHE_VALIDITY
                    ) {
                        val networkMovieDto = swapiService.getMovieById(id)
                        val movieEntity = networkMovieDto.toEntity()
                        if (movieEntity != null) {
                            movieDao.insertMovie(movieEntity)
                        }
                    }
                } catch (e: Exception) {
                    Timber.e(e, "Network error fetching movie by ID")
                }
            }.map { it?.toDomain() }

    /**
     * Fetches all movies from the remote API.
     */
    suspend fun refreshAllMoviesFromNetwork() {
        try {
            val networkMoviesDto = swapiService.getAllMovies().results
            movieDao.deleteAllMovies()
            movieDao.insertAllMovies(networkMoviesDto.toEntityList())
        } catch (e: Exception) {
            Timber.e(e, "Error refreshing movies from network")
        }
    }

    //endregion

}
