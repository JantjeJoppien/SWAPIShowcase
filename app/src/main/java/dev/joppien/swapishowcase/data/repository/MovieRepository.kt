package dev.joppien.swapishowcase.data.repository

import dev.joppien.swapishowcase.data.remote.api.SwapiClient
import dev.joppien.swapishowcase.data.remote.dto.MovieDTO

class MovieRepository {

    //region Public API

    suspend fun getAllMovies(): List<MovieDTO> {
        return fetchAll()
    }

    suspend fun getMovieById(id: Int): MovieDTO? {
        return fetchById(id)
    }

    //endregion

    //region Private API

    private suspend fun fetchAll(): List<MovieDTO> {
        return try {
            SwapiClient.swapiService.getAllMovies().results
        } catch (e: Exception) {
            emptyList()
        }
    }

    private suspend fun fetchById(id: Int): MovieDTO? {
        return try {
            SwapiClient.swapiService.getMovieById(id)
        } catch (e: Exception) {
            null
        }
    }

    //endregion

}