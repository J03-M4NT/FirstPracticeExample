package com.example.firstpracticeexample.presentation.home

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.firstpracticeexample.presentation.navigation.AppRoutes


@Composable
// Pagina principal
fun HomeScreen(navController: NavController){

    // values:



    // -------

    Spacer(modifier = Modifier.height(8.dp))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Pantalla de Bienvenida:
        Text(text = "Bienvenido a la aplicacion MultiUsos - PC1 DAM 2025-2")
        Spacer(modifier = Modifier.height(16.dp))

        Text("Menú Principal", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(32.dp))

        Button(onClick = { navController.navigate(AppRoutes.CALCULADORA_AGUA) }, modifier = Modifier.fillMaxWidth(0.8f)) {
            Text("Calculadora de Consumo de Agua")
        }
        Spacer(Modifier.height(16.dp))

        Button(onClick = { navController.navigate(AppRoutes.REGISTRO_ACTIVIDAD) }, modifier = Modifier.fillMaxWidth(0.8f)) {
            Text("Registro de Actividad Física")
        }
        Spacer(Modifier.height(16.dp))

        Button(onClick = { navController.navigate(AppRoutes.CATALOGO_AUTOS) }, modifier = Modifier.fillMaxWidth(0.8f)) {
            Text("Catálogo de Autos Deportivos")
        }

    }


}