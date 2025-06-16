package dev.joppien.swapishowcase.data.repository

import dev.joppien.swapishowcase.data.local.AppDatabase.Companion.DATABASE_CACHE_VALIDITY
import dev.joppien.swapishowcase.data.local.dao.PlanetDao
import dev.joppien.swapishowcase.data.mappers.toDomain
import dev.joppien.swapishowcase.data.mappers.toEntity
import dev.joppien.swapishowcase.data.mappers.toEntityList
import dev.joppien.swapishowcase.data.remote.api.SwapiClient
import dev.joppien.swapishowcase.domain.model.Planet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlanetRepository @Inject constructor(
    private val swapiClient: SwapiClient,
    private val planetDao: PlanetDao,
) {

    //region Public API

    fun getAllPlanets(): Flow<List<Planet>> =
        planetDao.getAllPlanets()
            .onStart {
                try {
                    val currentCache = planetDao.getAllPlanets().firstOrNull()
                    if (currentCache.isNullOrEmpty()
                        || currentCache.any { it.lastRefreshed < System.currentTimeMillis() - DATABASE_CACHE_VALIDITY }
                    ) {
                        val networkPlanetsDtoResults =
                            swapiClient.swapiService.getAllPlanets().results
                        planetDao.deleteAllPlanets()
                        planetDao.insertAllPlanets(networkPlanetsDtoResults.toEntityList())
                    }
                } catch (e: Exception) {
                    //ToDo: Handle error
                    println("Network error fetching all planets: ${e.message}")
                }
            }.map { it.toDomain() }

    fun getPlanetById(id: Int): Flow<Planet?> =
        planetDao.getPlanetById(id)
            .onStart {
                try {
                    val currentCachedPlanet = planetDao.getPlanetById(id).firstOrNull()
                    if (currentCachedPlanet == null
                        || currentCachedPlanet.lastRefreshed < System.currentTimeMillis() - DATABASE_CACHE_VALIDITY
                    ) {
                        val networkPlanetDto = swapiClient.swapiService.getPlanetById(id)
                        val planetEntity = networkPlanetDto.toEntity()
                        if (planetEntity != null) {
                            planetDao.insertPlanet(planetEntity)
                        }
                    }
                } catch (e: Exception) {
                    //ToDo: Handle error
                    println("Network error fetching planet by ID $id: ${e.message}")
                }
            }.map { it?.toDomain() }

    suspend fun refreshAllPlanetsFromNetwork() {
        try {
            val networkPlanetsDtoResults = swapiClient.swapiService.getAllPlanets().results
            planetDao.deleteAllPlanets()
            planetDao.insertAllPlanets(networkPlanetsDtoResults.toEntityList())
        } catch (e: Exception) {
            //ToDo: Handle error
            println("Error refreshing planets from network: ${e.message}")
        }
    }

    //endregion

}