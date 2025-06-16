package dev.joppien.swapishowcase.data.repository

import dev.joppien.swapishowcase.data.local.AppDatabase.Companion.DATABASE_CACHE_VALIDITY
import dev.joppien.swapishowcase.data.local.dao.VehicleDao
import dev.joppien.swapishowcase.data.mappers.toDomain
import dev.joppien.swapishowcase.data.mappers.toEntity
import dev.joppien.swapishowcase.data.mappers.toEntityList
import dev.joppien.swapishowcase.data.remote.api.SwapiService
import dev.joppien.swapishowcase.domain.model.Vehicle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehicleRepository @Inject constructor(
    private val swapiService: SwapiService,
    private val vehicleDao: VehicleDao,
) {

    //region Public API

    fun getAllVehicles(): Flow<List<Vehicle>> =
        vehicleDao.getAllVehicles()
            .onStart {
                try {
                    val currentCache = vehicleDao.getAllVehicles().firstOrNull()
                    if (currentCache.isNullOrEmpty() ||
                        currentCache.any { it.lastRefreshed < System.currentTimeMillis() - DATABASE_CACHE_VALIDITY }
                    ) {
                        val networkVehiclesDtoResults =
                            swapiService.getAllVehicles().results
                        vehicleDao.deleteAllVehicles()
                        vehicleDao.insertAllVehicles(networkVehiclesDtoResults.toEntityList())
                    }
                } catch (e: Exception) {
                    Timber.e(e, "Network error fetching all vehicles")
                }
            }.map { it.toDomain() }

    fun getVehicleById(id: Int): Flow<Vehicle?> =
        vehicleDao.getVehicleById(id)
            .onStart {
                try {
                    val currentCachedVehicle = vehicleDao.getVehicleById(id).firstOrNull()
                    if (currentCachedVehicle == null ||
                        currentCachedVehicle.lastRefreshed < System.currentTimeMillis() - DATABASE_CACHE_VALIDITY
                    ) {
                        val networkVehicleDto = swapiService.getVehicleById(id)
                        val vehicleEntity = networkVehicleDto.toEntity()
                        if (vehicleEntity != null) {
                            vehicleDao.insertVehicle(vehicleEntity)
                        }
                    }
                } catch (e: Exception) {
                    Timber.e(e, "Network error fetching vehicle by ID")
                }
            }.map { it?.toDomain() }

    suspend fun refreshAllVehiclesFromNetwork() {
        try {
            val networkVehiclesDtoResults = swapiService.getAllVehicles().results
            vehicleDao.deleteAllVehicles()
            vehicleDao.insertAllVehicles(networkVehiclesDtoResults.toEntityList())
        } catch (e: Exception) {
            Timber.e(e, "Error refreshing vehicles from network")
        }
    }

    //endregion

}