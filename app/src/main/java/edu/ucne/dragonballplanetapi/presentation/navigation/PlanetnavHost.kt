package edu.ucne.dragonballplanetapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.dragonballplanetapi.presentation.List.ListScreen
import edu.ucne.dragonballplanetapi.presentation.detail.DetailScreen

@Composable
fun PlanetNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.PlanetList
    ) {
        composable<Screen.PlanetList> {
            ListScreen(
                onPlanetClick = { id -> navHostController.navigate(Screen.Detail(id)) }
            )
        }

        composable<Screen.Detail> {
            DetailScreen(
                onBack = { navHostController.navigateUp() }
            )
        }
    }
}