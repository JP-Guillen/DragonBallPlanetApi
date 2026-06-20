package edu.ucne.dragonballplanetapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.dragonballplanetapi.presentation.navigation.PlanetNavHost
import edu.ucne.dragonballplanetapi.ui.theme.DragonBallPlanetApiTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DragonBallPlanetApiTheme {
                val navHostController = rememberNavController()
                PlanetNavHost(navHostController = navHostController)
            }
        }
    }
}