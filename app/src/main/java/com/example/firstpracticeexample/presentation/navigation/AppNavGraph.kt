package com.example.firstpracticeexample.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstpracticeexample.presentation.home.HomeScreen
import com.example.firstpracticeexample.presentation.register.RegisterScreen
import com.example.firstpracticeexample.presentation.calculator.CalculatorScreen
import com.example.firstpracticeexample.presentation.catalogue.CatalogueScreen

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
        startDestination = AppRoutes.MENU_PRINCIPAL){

        // Menú Principal
        composable(AppRoutes.MENU_PRINCIPAL) { HomeScreen(navController = navController)}

        // Calculadora de Agua
        composable(AppRoutes.CALCULADORA_AGUA) { CalculatorScreen(navController = navController) }

        // Registro de actividad física
        composable(AppRoutes.REGISTRO_ACTIVIDAD) { RegisterScreen(navController = navController) }

        // Catalogo de Autos
        composable(AppRoutes.CATALOGO_AUTOS) { CatalogueScreen(navController = navController) }


    }

}