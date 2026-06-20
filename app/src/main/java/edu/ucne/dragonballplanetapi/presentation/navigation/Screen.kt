package edu.ucne.dragonballplanetapi.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object PlanetList : Screen()

    @Serializable
    data class Detail(val id: Int) : Screen()
}