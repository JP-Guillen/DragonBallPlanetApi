package edu.ucne.dragonballplanetapi.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import edu.ucne.dragonballplanetapi.domain.model.Planet
import edu.ucne.dragonballplanetapi.ui.theme.DragonBallPlanetApiTheme

@Composable
fun DetailScreen(
    onBack: () -> Unit,
    viewModel: DetailPlanetViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    DetailBody(state = state, onBack = onBack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailBody(
    state: DetailPlanetUiState,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Planeta") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->
        state.planet?.let { planet ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model = planet.image,
                    contentDescription = planet.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )

                Text(planet.name, style = MaterialTheme.typography.headlineMedium)
                Text("Estado: ${if (planet.isDestroyed) "Destruido" else "En pie"}")
                Text(planet.description)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailBodyPreview() {
    DragonBallPlanetApiTheme {
        DetailBody(
            state = DetailPlanetUiState(
                planet = Planet(
                    id = 1,
                    name = "Namek",
                    isDestroyed = true,
                    description = "Planeta natal de los Namekianos. Escenario de importantes batallas.",
                    image = ""
                )
            ),
            onBack = {}
        )
    }
}