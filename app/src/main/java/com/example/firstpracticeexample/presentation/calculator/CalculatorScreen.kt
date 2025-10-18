package com.example.firstpracticeexample.presentation.calculator


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// Calculadora de Agua
fun CalculatorScreen(navController: NavController){

    var nombre by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var generoSeleccionado by remember { mutableStateOf<String?>(null) } // Puede ser nulo al inicio
    var resultado by remember { mutableStateOf("") }

    val generos = listOf("Masculino", "Femenino", "Sin especificar")
    val context = LocalContext.current // Contexto para mostrar Toasts/Snackbars


    // Scaffold:
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calculadora de Agua") },
                // Botón para regresar al menú principal
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        // Contenido principal de la pantalla
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Padding para no solaparse con la TopAppBar
                .padding(16.dp), // Padding adicional para el contenido
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // --- CAMPOS DE ENTRADA ---
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next) // Botón 'Siguiente' en el teclado
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = peso,
                onValueChange = { peso = it },
                label = { Text("Peso corporal (en kg)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, // Teclado numérico
                    imeAction = ImeAction.Done        // Botón 'Hecho' en el teclado
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- RADIO BUTTONS PARA GÉNERO ---
            Text("Género:", modifier = Modifier.fillMaxWidth())
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                generos.forEach { genero ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = (genero == generoSeleccionado),
                            onClick = { generoSeleccionado = genero }
                        )
                        Text(text = genero, modifier = Modifier.padding(start = 4.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            // --- BOTÓN DE CÁLCULO ---
            Button(
                onClick = {
                    // --- VALIDACIÓN Y LÓGICA DE CÁLCULO ---
                    val pesoDouble = peso.toDoubleOrNull()
                    when {
                        nombre.isBlank() -> {
                            Toast.makeText(context, "El nombre es obligatorio.", Toast.LENGTH_SHORT).show()
                        }
                        pesoDouble == null || pesoDouble <= 5 || pesoDouble > 200 -> {
                            Toast.makeText(context, "El peso debe ser un número entre 5 y 200.", Toast.LENGTH_SHORT).show()
                        }
                        generoSeleccionado == null -> {
                            Toast.makeText(context, "Debe seleccionar un género.", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            // Si todo es válido, calculamos
                            val factorGenero = when (generoSeleccionado) {
                                "Masculino" -> 1.02
                                "Femenino" -> 1.01
                                else -> 1.00
                            }
                            val litros = pesoDouble * 0.035 * factorGenero
                            resultado = "$nombre debe beber aproximadamente %.2f litros de agua al día".format(litros)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular")
            }
            Spacer(modifier = Modifier.height(24.dp))

            // --- MOSTRAR RESULTADO ---
            if (resultado.isNotBlank()) {
                Text(
                    text = resultado,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}