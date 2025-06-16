package dev.joppien.swapishowcase.data.repository

import dev.joppien.swapishowcase.data.local.AppDatabase.Companion.DATABASE_CACHE_VALIDITY
import dev.joppien.swapishowcase.data.local.dao.SpeciesDao
import dev.joppien.swapishowcase.data.mappers.toDomain
import dev.joppien.swapishowcase.data.mappers.toEntity
import dev.joppien.swapishowcase.data.mappers.toEntityList
import dev.joppien.swapishowcase.data.remote.api.SwapiService
import dev.joppien.swapishowcase.domain.model.Species
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpeciesRepository @Inject constructor(
    private val swapiService: SwapiService,
    private val speciesDao: SpeciesDao,
) {

    //region Public API

    fun getAllSpecies(): Flow<List<Species>> =
        speciesDao.getAllSpecies()
            .onStart {
                try {
                    val currentCache = speciesDao.getAllSpecies().firstOrNull()
                    if (currentCache.isNullOrEmpty() ||
                        currentCache.any { it.lastRefreshed < System.currentTimeMillis() - DATABASE_CACHE_VALIDITY }
                    ) {
                        val networkSpeciesDtoResults =
                            swapiService.getAllSpecies().results
                        speciesDao.deleteAllSpecies()
                        speciesDao.insertAllSpecies(networkSpeciesDtoResults.toEntityList())
                    }
                } catch (e: Exception) {
                    Timber.e(e, "Network error fetching all species")
                }
            }.map { it.toDomain() }

    fun getSpeciesById(id: Int): Flow<Species?> =
        speciesDao.getSpeciesById(id)
            .onStart {
                try {
                    val currentCachedSpecies = speciesDao.getSpeciesById(id).firstOrNull()
                    if (currentCachedSpecies == null ||
                        currentCachedSpecies.lastRefreshed < System.currentTimeMillis() - DATABASE_CACHE_VALIDITY
                    ) {
                        val networkSpeciesDto = swapiService.getSpeciesById(id)
                        val speciesEntity = networkSpeciesDto.toEntity()
                        if (speciesEntity != null) {
                            speciesDao.insertSpecies(speciesEntity)
                        }
                    }
                } catch (e: Exception) {
                    Timber.e(e, "Network error fetching species by ID")
                }
            }.map { it?.toDomain() }

    suspend fun refreshAllSpeciesFromNetwork() {
        try {
            val networkSpeciesDtoResults = swapiService.getAllSpecies().results
            speciesDao.deleteAllSpecies()
            speciesDao.insertAllSpecies(networkSpeciesDtoResults.toEntityList())
        } catch (e: Exception) {
            Timber.e(e, "Error refreshing species from network")
        }
    }

    //endregion

}