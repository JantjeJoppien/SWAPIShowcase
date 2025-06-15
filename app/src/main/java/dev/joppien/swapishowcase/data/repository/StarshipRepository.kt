package dev.joppien.swapishowcase.data.repository

import dev.joppien.swapishowcase.data.local.AppDatabase.Companion.DATABASE_CACHE_VALIDITY
import dev.joppien.swapishowcase.data.local.dao.StarshipDao
import dev.joppien.swapishowcase.data.local.entity.StarshipEntity
import dev.joppien.swapishowcase.data.mappers.toEntity
import dev.joppien.swapishowcase.data.mappers.toEntityList
import dev.joppien.swapishowcase.data.remote.api.SwapiClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StarshipRepository @Inject constructor(
    private val swapiClient: SwapiClient,
    private val starshipDao: StarshipDao,
) {

    //region Public API

    fun getAllStarships(): Flow<List<StarshipEntity>> =
        starshipDao.getAllStarships()
            .onStart {
                try {
                    val currentCache = starshipDao.getAllStarships().firstOrNull()
                    if (currentCache.isNullOrEmpty() ||
                        currentCache.any { it.lastRefreshed < System.currentTimeMillis() - DATABASE_CACHE_VALIDITY }
                    ) {
                        val networkStarshipsDtoResults =
                            swapiClient.swapiService.getAllStarships().results
                        starshipDao.deleteAllStarships()
                        starshipDao.insertAllStarships(networkStarshipsDtoResults.toEntityList())
                    }
                } catch (e: Exception) {
                    //ToDo: Handle error
                    println("Network error fetching all starships: ${e.message}")
                }
            }

    fun getStarshipById(id: Int): Flow<StarshipEntity?> =
        starshipDao.getStarshipById(id)
            .onStart {
                try {
                    val currentCachedStarship = starshipDao.getStarshipById(id).firstOrNull()
                    if (currentCachedStarship == null ||
                        currentCachedStarship.lastRefreshed < System.currentTimeMillis() - DATABASE_CACHE_VALIDITY
                    ) {
                        val networkStarshipDto = swapiClient.swapiService.getStarshipById(id)
                        val starshipEntity = networkStarshipDto.toEntity()
                        if (starshipEntity != null) {
                            starshipDao.insertStarship(starshipEntity)
                        }
                    }
                } catch (e: Exception) {
                    //ToDo: Handle error
                    println("Network error fetching starship by ID $id: ${e.message}")
                }
            }

    suspend fun refreshAllStarshipsFromNetwork() {
        try {
            val networkStarshipsDtoResults = swapiClient.swapiService.getAllStarships().results
            starshipDao.deleteAllStarships()
            starshipDao.insertAllStarships(networkStarshipsDtoResults.toEntityList())
        } catch (e: Exception) {
            //ToDo: Handle error
            println("Error refreshing starships from network: ${e.message}")
        }
    }

    //endregion

}