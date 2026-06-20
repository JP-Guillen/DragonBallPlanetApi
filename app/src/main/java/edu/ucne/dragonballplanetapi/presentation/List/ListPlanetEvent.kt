package edu.ucne.dragonballplanetapi.presentation.List

sealed interface ListPlanetEvent {
    data class UpdateFilters(val name: String) : ListPlanetEvent
    data object Search : ListPlanetEvent
}