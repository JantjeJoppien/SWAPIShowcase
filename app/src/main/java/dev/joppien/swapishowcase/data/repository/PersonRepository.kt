package dev.joppien.swapishowcase.data.repository

import dev.joppien.swapishowcase.data.local.AppDatabase.Companion.DATABASE_CACHE_VALIDITY
import dev.joppien.swapishowcase.data.local.dao.PersonDao
import dev.joppien.swapishowcase.data.mappers.toDomain
import dev.joppien.swapishowcase.data.mappers.toEntity
import dev.joppien.swapishowcase.data.mappers.toEntityList
import dev.joppien.swapishowcase.data.remote.api.SwapiClient
import dev.joppien.swapishowcase.domain.model.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonRepository @Inject constructor(
    private val swapiClient: SwapiClient,
    private val personDao: PersonDao,
) {

    //region Public API

    fun getAllPeople(): Flow<List<Person>> =
        personDao.getAllPeople()
            .onStart {
                try {
                    val currentCache = personDao.getAllPeople().firstOrNull()
                    if (currentCache.isNullOrEmpty()
                        || currentCache.any { it.lastRefreshed < System.currentTimeMillis() - DATABASE_CACHE_VALIDITY }
                    ) {
                        val networkPeopleDtoResults =
                            swapiClient.swapiService.getAllPeople().results
                        personDao.deleteAllPeople()
                        personDao.insertAllPeople(networkPeopleDtoResults.toEntityList())
                    }
                } catch (e: Exception) {
                    //ToDo: Handle error
                    println("Network error fetching all people: ${e.message}")
                }
            }.map { it.toDomain() }

    fun getPersonById(id: Int): Flow<Person?> =
        personDao.getPersonById(id)
            .onStart {
                try {
                    val currentCachedPerson = personDao.getPersonById(id).firstOrNull()
                    if (currentCachedPerson == null
                        || currentCachedPerson.lastRefreshed < System.currentTimeMillis() - DATABASE_CACHE_VALIDITY
                    ) {

                        val networkPersonDto = swapiClient.swapiService.getPersonById(id)
                        val personEntity = networkPersonDto.toEntity()
                        if (personEntity != null) {
                            personDao.insertPerson(personEntity)
                        }
                    }
                } catch (e: Exception) {
                    //ToDo: Handle error
                    println("Network error fetching person by ID $id: ${e.message}")
                }
            }.map { it?.toDomain() }

    suspend fun refreshAllPeopleFromNetwork() {
        try {
            val networkPeopleDtoResults = swapiClient.swapiService.getAllPeople().results
            personDao.deleteAllPeople()
            personDao.insertAllPeople(networkPeopleDtoResults.toEntityList())
        } catch (e: Exception) {
            //ToDo: Handle error
            println("Error refreshing people from network: ${e.message}")
        }
    }

    //endregion

}