package dev.joppien.swapishowcase.data.remote.api

import dev.joppien.swapishowcase.data.remote.dto.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SwapiService {

    //region People
    @GET("people/")
    suspend fun getAllPeople(@Query("page") page: Int? = null): PaginatedResponse<PersonDTO>

    @GET("people/{id}/")
    suspend fun getPersonById(@Path("id") personId: Int): PersonDTO
    //endregion

    //region Movies
    @GET("films/")
    suspend fun getAllMovies(@Query("page") page: Int? = null): PaginatedResponse<MovieDTO>

    @GET("films/{id}/")
    suspend fun getMovieById(@Path("id") movieId: Int): MovieDTO
    //endregion

    //region Starships
    @GET("starships/")
    suspend fun getAllStarships(@Query("page") page: Int? = null): PaginatedResponse<StarshipDTO>

    @GET("starships/{id}/")
    suspend fun getStarshipById(@Path("id") starshipId: Int): StarshipDTO
    //endregion

    //region Vehicles
    @GET("vehicles/")
    suspend fun getAllVehicles(@Query("page") page: Int? = null): PaginatedResponse<VehicleDTO>

    @GET("vehicles/{id}/")
    suspend fun getVehicleById(@Path("id") vehicleId: Int): VehicleDTO
    //endregion

    //region Species
    @GET("species/")
    suspend fun getAllSpecies(@Query("page") page: Int? = null): PaginatedResponse<SpeciesDTO>

    @GET("species/{id}/")
    suspend fun getSpeciesById(@Path("id") speciesId: Int): SpeciesDTO
    //endregion

    //region Planets
    @GET("planets/")
    suspend fun getAllPlanets(@Query("page") page: Int? = null): PaginatedResponse<PlanetDTO>

    @GET("planets/{id}/")
    suspend fun getPlanetById(@Path("id") planetId: Int): PlanetDTO
    //endregion
}