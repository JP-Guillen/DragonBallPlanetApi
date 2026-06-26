package edu.ucne.dragonballplanetapi.presentation.planet.detail

import edu.ucne.dragonballplanetapi.domain.planet.model.Planet

data class DetailPlanetUiState(
    val isLoading: Boolean = false,
    val planet: Planet? = null,
    val error: String? = null
)