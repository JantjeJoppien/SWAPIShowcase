package dev.joppien.swapishowcase.data.repository

import dev.joppien.swapishowcase.data.local.AppDatabase.Companion.DATABASE_CACHE_VALIDITY
import dev.joppien.swapishowcase.data.local.dao.SpeciesDao
import dev.joppien.swapishowcase.data.local.entity.SpeciesEntity
import dev.joppien.swapishowcase.data.mappers.toEntity
import dev.joppien.swapishowcase.data.mappers.toEntityList
import dev.joppien.swapishowcase.data.remote.api.SwapiClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpeciesRepository @Inject constructor(
    private val swapiClient: SwapiClient,
    private val speciesDao: SpeciesDao,
) {

    //region Public API

    fun getAllSpecies(): Flow<List<SpeciesEntity>> =
        speciesDao.getAllSpecies()
            .onStart {
                try {
                    val currentCache = speciesDao.getAllSpecies().firstOrNull()
                    if (currentCache.isNullOrEmpty() ||
                        currentCache.any { it.lastRefreshed < System.currentTimeMillis() - DATABASE_CACHE_VALIDITY }
                    ) {
                        val networkSpeciesDtoResults =
                            swapiClient.swapiService.getAllSpecies().results
                        speciesDao.deleteAllSpecies()
                        speciesDao.insertAllSpecies(networkSpeciesDtoResults.toEntityList())
                    }
                } catch (e: Exception) {
                    //ToDo: Handle error
                    println("Network error fetching all species: ${e.message}")
                }
            }

    fun getSpeciesById(id: Int): Flow<SpeciesEntity?> =
        speciesDao.getSpeciesById(id)
            .onStart {
                try {
                    val currentCachedSpecies = speciesDao.getSpeciesById(id).firstOrNull()
                    if (currentCachedSpecies == null ||
                        currentCachedSpecies.lastRefreshed < System.currentTimeMillis() - DATABASE_CACHE_VALIDITY
                    ) {
                        val networkSpeciesDto = swapiClient.swapiService.getSpeciesById(id)
                        val speciesEntity = networkSpeciesDto.toEntity()
                        if (speciesEntity != null) {
                            speciesDao.insertSpecies(speciesEntity)
                        }
                    }
                } catch (e: Exception) {
                    //ToDo: Handle error
                    println("Network error fetching species by ID $id: ${e.message}")
                }
            }

    suspend fun refreshAllSpeciesFromNetwork() {
        try {
            val networkSpeciesDtoResults = swapiClient.swapiService.getAllSpecies().results
            speciesDao.deleteAllSpecies()
            speciesDao.insertAllSpecies(networkSpeciesDtoResults.toEntityList())
        } catch (e: Exception) {
            //ToDo: Handle error
            println("Error refreshing species from network: ${e.message}")
        }
    }

    //endregion

}