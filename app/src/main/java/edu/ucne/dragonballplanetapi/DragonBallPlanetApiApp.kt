package edu.ucne.dragonballplanetapi

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DragonBallPlanetApiApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}