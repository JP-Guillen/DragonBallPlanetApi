package edu.ucne.dragonballplanetapi.presentation.character.detail

import edu.ucne.dragonballplanetapi.domain.character.model.Character

data class DetailUiState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val error: String? = null
)