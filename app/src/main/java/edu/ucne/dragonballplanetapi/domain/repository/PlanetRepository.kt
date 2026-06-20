package edu.ucne.dragonballplanetapi.domain.repository

import edu.ucne.dragonballplanetapi.data.remote.Resource
import edu.ucne.dragonballplanetapi.domain.model.Planet
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {
    fun getPlanets(
        page: Int,
        limit: Int,
        name: String?
    ): Flow<Resource<List<Planet>>>

    fun getPlanetDetail(id: Int): Flow<Resource<Planet>>
}