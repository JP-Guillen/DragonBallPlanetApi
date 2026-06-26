package edu.ucne.dragonballplanetapi.presentation.character.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import edu.ucne.dragonballplanetapi.domain.character.model.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    state: DetailUiState,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail del Personaje") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.character?.let { character ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = character.image,
                    contentDescription = character.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(character.name, style = MaterialTheme.typography.headlineMedium)
                Text("Raza: ${character.race}", style = MaterialTheme.typography.bodyLarge)
                Text("Género: ${character.gender}", style = MaterialTheme.typography.bodyLarge)
                Text("Ki: ${character.ki}", style = MaterialTheme.typography.bodyLarge)
                Text("Máx Ki: ${character.maxKi}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(character.description, style = MaterialTheme.typography.bodyMedium)
            }
        }

        state.error?.let { error ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(text = error, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val sampleCharacter = Character(
        id = 1,
        name = "Goku",
        ki = "60.000.000",
        race = "Saiyan",
        gender = "Male",
        description = "The main protagonist of the series.",
        image = "",
        maxKi = "90.000.000.000"
    )
    val state = DetailUiState(
        character = sampleCharacter
    )

    MaterialTheme {
        Surface {
            DetailScreen(
                state = state,
                onBack = {}
            )
        }
    }
}