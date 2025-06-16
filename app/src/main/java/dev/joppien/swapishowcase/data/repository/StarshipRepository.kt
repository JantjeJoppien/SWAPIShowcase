package dev.joppien.swapishowcase.data.repository

import dev.joppien.swapishowcase.data.local.AppDatabase.Companion.DATABASE_CACHE_VALIDITY
import dev.joppien.swapishowcase.data.local.dao.StarshipDao
import dev.joppien.swapishowcase.data.mappers.toDomain
import dev.joppien.swapishowcase.data.mappers.toEntity
import dev.joppien.swapishowcase.data.mappers.toEntityList
import dev.joppien.swapishowcase.data.remote.api.SwapiService
import dev.joppien.swapishowcase.domain.model.Starship
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StarshipRepository @Inject constructor(
    private val swapiService: SwapiService,
    private val starshipDao: StarshipDao,
) {

    //region Public API

    fun getAllStarships(): Flow<List<Starship>> =
        starshipDao.getAllStarships()
            .onStart {
                try {
                    val currentCache = starshipDao.getAllStarships().firstOrNull()
                    if (currentCache.isNullOrEmpty() ||
                        currentCache.any { it.lastRefreshed < System.currentTimeMillis() - DATABASE_CACHE_VALIDITY }
                    ) {
                        val networkStarshipsDtoResults =
                            swapiService.getAllStarships().results
                        starshipDao.deleteAllStarships()
                        starshipDao.insertAllStarships(networkStarshipsDtoResults.toEntityList())
                    }
                } catch (e: Exception) {
                    Timber.e(e, "Network error fetching all starships")
                }
            }.map { it.toDomain() }

    fun getStarshipById(id: Int): Flow<Starship?> =
        starshipDao.getStarshipById(id)
            .onStart {
                try {
                    val currentCachedStarship = starshipDao.getStarshipById(id).firstOrNull()
                    if (currentCachedStarship == null ||
                        currentCachedStarship.lastRefreshed < System.currentTimeMillis() - DATABASE_CACHE_VALIDITY
                    ) {
                        val networkStarshipDto = swapiService.getStarshipById(id)
                        val starshipEntity = networkStarshipDto.toEntity()
                        if (starshipEntity != null) {
                            starshipDao.insertStarship(starshipEntity)
                        }
                    }
                } catch (e: Exception) {
                    Timber.e(e, "Network error fetching starship by ID")
                }
            }.map { it?.toDomain() }

    suspend fun refreshAllStarshipsFromNetwork() {
        try {
            val networkStarshipsDtoResults = swapiService.getAllStarships().results
            starshipDao.deleteAllStarships()
            starshipDao.insertAllStarships(networkStarshipsDtoResults.toEntityList())
        } catch (e: Exception) {
            Timber.e(e, "Error refreshing starships from network")
        }
    }

    //endregion

}