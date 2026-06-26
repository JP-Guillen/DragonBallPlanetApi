package edu.ucne.dragonballplanetapi.data.remote.dto

import edu.ucne.dragonballplanetapi.domain.planet.model.Planet

data class PlanetResponseDto(
    val items: List<PlanetDto>
)

data class PlanetDto(
    val id: Int,
    val name: String,
    val isDestroyed: Boolean,
    val description: String,
    val image: String
) {
    fun toDomain() = Planet(
        id, name, isDestroyed, description, image
    )
}