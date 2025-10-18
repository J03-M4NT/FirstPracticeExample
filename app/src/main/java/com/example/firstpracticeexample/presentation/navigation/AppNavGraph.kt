package com.example.firstpracticeexample.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstpracticeexample.presentation.home.HomeScreen

object AppRoutes {
    const val MENU_PRINCIPAL = "menu_principal"
    const val CALCULADORA_AGUA = "calculadora_agua"
    const val REGISTRO_ACTIVIDAD = "registro_actividad"
    const val CATALOGO_AUTOS = "catalogo_autos"
}

@Composable
fun AppNavGraph(){

    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = "home"){

        composable("home") { HomeScreen()}

    }

}