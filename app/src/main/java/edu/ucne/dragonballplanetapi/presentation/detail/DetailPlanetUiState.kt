package edu.ucne.dragonballplanetapi.presentation.detail

import edu.ucne.dragonballplanetapi.domain.model.Planet

data class DetailPlanetUiState(
    val isLoading: Boolean = false,
    val planet: Planet? = null,
    val error: String? = null
)