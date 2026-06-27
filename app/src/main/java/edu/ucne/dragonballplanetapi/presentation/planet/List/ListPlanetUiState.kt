package edu.ucne.dragonballplanetapi.presentation.planet.List

import edu.ucne.dragonballplanetapi.domain.planet.model.Planet

data class ListPlanetUiState(
    val isLoading: Boolean = false,
    val planets: List<Planet> = emptyList(),
    val error: String? = null,
    val filterName: String = ""
)