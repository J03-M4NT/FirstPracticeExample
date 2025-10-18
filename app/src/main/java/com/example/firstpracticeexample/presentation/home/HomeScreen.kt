package com.example.firstpracticeexample.presentation.home

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
// Pagina principal
fun HomeScreen(){

    // values:



    // -------

    Spacer(modifier = Modifier.height(8.dp))

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {

        // Pantalla de Bienvenida:
        Text(text = "Bienvenido a la aplicacion MultiUsos - PC1 DAM 2025-2")
        Spacer(modifier = Modifier.height(8.dp))



    }


}