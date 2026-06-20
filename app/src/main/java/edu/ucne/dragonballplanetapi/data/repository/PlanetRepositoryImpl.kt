package edu.ucne.dragonballplanetapi.data.repository

import edu.ucne.dragonballplanetapi.data.remote.Resource
import edu.ucne.dragonballplanetapi.data.remote.remotedatasource.PlanetRemoteDataSource
import edu.ucne.dragonballplanetapi.domain.model.Planet
import edu.ucne.dragonballplanetapi.domain.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val remoteDataSource: PlanetRemoteDataSource
) : PlanetRepository {

    override fun getPlanets(
        page: Int,
        limit: Int,
        name: String?
    ): Flow<Resource<List<Planet>>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.getPlanets(page, limit, null)
        response.onSuccess { planets ->
            var list = planets.items.map { it.toDomain() }
            if (!name.isNullOrBlank()) {
                list = list.filter { it.name.contains(name, ignoreCase = true) }
            }
            emit(Resource.Success(list))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }

    override fun getPlanetDetail(id: Int): Flow<Resource<Planet>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.getPlanetDetail(id)
        response.onSuccess { planet ->
            emit(Resource.Success(planet.toDomain()))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }
}