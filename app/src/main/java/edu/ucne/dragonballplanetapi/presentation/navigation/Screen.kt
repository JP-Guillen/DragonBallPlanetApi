package edu.ucne.dragonballplanetapi.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object PlanetList : Screen()

    @Serializable
    data object CharacterList : Screen()

    @Serializable
    data class CharacterDetail(val id: Int) : Screen()

    @Serializable
    data class PlanetDetail(val id: Int) : Screen()
}