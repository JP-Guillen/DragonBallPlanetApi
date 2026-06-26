package edu.ucne.dragonballplanetapi.presentation.character.List

import edu.ucne.dragonballplanetapi.domain.character.model.Character

data class CharacterListUiState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String? = null,
    val filterName: String = "",
    val filterGender: String = "",
    val filterRace: String = ""
)