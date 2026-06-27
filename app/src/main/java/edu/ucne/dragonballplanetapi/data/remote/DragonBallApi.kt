package edu.ucne.dragonballplanetapi.data.remote

import edu.ucne.dragonballplanetapi.data.remote.dto.CharacterDto
import edu.ucne.dragonballplanetapi.data.remote.dto.CharacterResponseDto
import edu.ucne.dragonballplanetapi.data.remote.dto.PlanetDto
import edu.ucne.dragonballplanetapi.data.remote.dto.PlanetResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DragonBallApi {

    @GET("planets")
    suspend fun getPlanets(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("name") name: String?
    ): Response<PlanetResponseDto>

    @GET("planets/{id}")
    suspend fun getPlanetDetail(
        @Path("id") id: Int
    ): Response<PlanetDto>

    @GET("planets")
    suspend fun searchPlanets(
        @Query("name") name: String
    ): Response<List<PlanetDto>>

    @GET("characters")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("name") name: String?,
        @Query("gender") gender: String?,
        @Query("race") race: String?
    ): Response<CharacterResponseDto>

    @GET("characters/{id}")
    suspend fun getCharacterDetail(
        @Path("id") id: Int
    ): Response<CharacterDto>

    @GET("characters")
    suspend fun searchCharacters(
        @Query("name") name: String?,
        @Query("gender") gender: String?,
        @Query("race") race: String?
    ): Response<List<CharacterDto>>
}