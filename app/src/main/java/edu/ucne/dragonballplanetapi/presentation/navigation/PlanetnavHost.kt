package edu.ucne.dragonballplanetapi.presentation.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import edu.ucne.dragonballplanetapi.presentation.character.List.ListScreen as CharacterListScreen
import edu.ucne.dragonballplanetapi.presentation.character.detail.DetailViewModel as CharacterDetailViewModel
import edu.ucne.dragonballplanetapi.presentation.character.detail.DetailScreen as CharacterDetailScreen
import edu.ucne.dragonballplanetapi.presentation.planet.List.ListScreen as PlanetListScreen
import edu.ucne.dragonballplanetapi.presentation.planet.detail.DetailPlanetViewModel
import edu.ucne.dragonballplanetapi.presentation.planet.detail.DetailScreen as PlanetDetailScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetNavHost(navHostController: NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val tituloBarra = when {
        currentRoute?.contains("CharacterList") == true -> "Personajes Dragon Ball"
        currentRoute?.contains("PlanetList") == true -> "Planetas Dragon Ball"
        else -> "Detalle"
    }

    val esPantallaLista = currentRoute?.contains("CharacterList") == true || currentRoute?.contains("PlanetList") == true

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Dragon Ball API",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))

                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.List, contentDescription = null) },
                    label = { Text("Personajes") },
                    selected = currentRoute?.contains("CharacterList") == true,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navHostController.navigate(Screen.CharacterList) {
                            popUpTo(Screen.CharacterList) { inclusive = false }
                            launchSingleTop = true
                        }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Place, contentDescription = null) },
                    label = { Text("Planetas") },
                    selected = currentRoute?.contains("PlanetList") == true,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navHostController.navigate(Screen.PlanetList) {
                            launchSingleTop = true
                        }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                if (esPantallaLista) {
                    CenterAlignedTopAppBar(
                        title = { Text(tituloBarra) },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch { drawerState.open() }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                }
            }
        ) { paddingValues ->
            NavHost(
                navController = navHostController,
                startDestination = Screen.CharacterList,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable<Screen.PlanetList> {
                    PlanetListScreen(
                        onPlanetClick = { id -> navHostController.navigate(Screen.PlanetDetail(id)) }
                    )
                }

                composable<Screen.CharacterList> {
                    CharacterListScreen(
                        onCharacterClick = { id -> navHostController.navigate(Screen.CharacterDetail(id)) }
                    )
                }

                composable<Screen.CharacterDetail> {
                    val viewModel: CharacterDetailViewModel = hiltViewModel()
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    CharacterDetailScreen(
                        state = state,
                        onBack = { navHostController.navigateUp() }
                    )
                }

                composable<Screen.PlanetDetail> {
                    val viewModel: DetailPlanetViewModel = hiltViewModel()
                    PlanetDetailScreen(
                        viewModel = viewModel,
                        onBack = { navHostController.navigateUp() }
                    )
                }
            }
        }
    }
}