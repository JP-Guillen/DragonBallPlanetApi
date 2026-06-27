package edu.ucne.dragonballplanetapi.presentation.character.List

sealed interface CharacterListEvent {
    data class UpdateFilters(
        val name: String,
        val gender: String,
        val race: String
    ) : CharacterListEvent

    data object Search : CharacterListEvent
}