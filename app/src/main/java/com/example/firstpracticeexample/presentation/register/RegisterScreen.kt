package com.example.firstpracticeexample.presentation.register

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// Data class:
data class Actividad(val nombre: String, val caloriasPorMinuto: Int)
enum class Intensidad(val factor: Double, val nombreMostrado: String) {
    BAJA(0.8, "Baja"),
    MEDIA(1.0, "Media"),
    ALTA(1.2, "Alta")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// Registro de Actividad Fisica
fun RegisterScreen(navController: NavController){


    // Listas de Datos para la UI
    val listaActividades = listOf(
        Actividad("Correr", 10),
        Actividad("Caminar", 5),
        Actividad("Nadar", 8),
        Actividad("Ciclismo", 7),
        Actividad("Yoga", 4)
    )
    val listaIntensidades = Intensidad.values().toList()

    // ESTADOS DE LA UI
    var actividadSeleccionada by remember { mutableStateOf<Actividad?>(null) }
    var duracion by remember { mutableStateOf("") }
    var intensidadSeleccionada by remember { mutableStateOf<Intensidad?>(null) }
    var resultado by remember { mutableStateOf("") }

    var dropdownExpandido by remember { mutableStateOf(false) } // Estado para el menú desplegable
    val context = LocalContext.current

    // ESTRUCTURA DE LA PANTALLA
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro de Actividad Física") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Regresar")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // --- MENÚ DESPLEGABLE (DROPDOWN) PARA ACTIVIDAD ---

            // Se envuelve en un Box
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                // El OutlinedTextField va primero, como fondo.
                OutlinedTextField(
                    value = actividadSeleccionada?.nombre ?: "Seleccione una actividad",
                    onValueChange = {},
                    readOnly = true, // Para que el usuario no pueda escribir
                    label = { Text("Tipo de Actividad") },
                    trailingIcon = { Icon(Icons.Default.ArrowDropDown, "Abrir menú") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { dropdownExpandido = true } // Al hacer clic, se expande el menú
                )

                // El DropdownMenu ahora es "hermano" del OutlinedTextField, no "hijo".
                // Se dibujará encima del contenido cuando `expanded` sea true.
                DropdownMenu(
                    expanded = dropdownExpandido,
                    onDismissRequest = { dropdownExpandido = false },
                    modifier = Modifier.fillMaxWidth() // Haz que el menú ocupe el ancho del Box
                ) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shadowElevation = 8.dp // Esta sombra lo hará visible.
                    ) {
                        //
                        Column {
                            listaActividades.forEach { actividad ->
                                DropdownMenuItem(
                                    text = { Text(actividad.nombre) },
                                    onClick = {
                                        actividadSeleccionada = actividad
                                        dropdownExpandido = false // Cierra el menú al seleccionar
                                    }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- CAMPO DE DURACIÓN ---
            OutlinedTextField(
                value = duracion,
                onValueChange = { duracion = it },
                label = { Text("Duración (en minutos)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

            // RADIO BUTTONS PARA INTENSIDAD
            Text("Intensidad:", modifier = Modifier.fillMaxWidth())
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listaIntensidades.forEach { intensidad ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = (intensidad == intensidadSeleccionada),
                            onClick = { intensidadSeleccionada = intensidad }
                        )
                        Text(text = intensidad.nombreMostrado, modifier = Modifier.padding(start = 4.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            // Boton de Calculo
            Button(
                onClick = {
                    // Validacion y Lógica
                    val duracionInt = duracion.toIntOrNull()
                    when {
                        actividadSeleccionada == null -> {
                            Toast.makeText(context, "Debe seleccionar un tipo de actividad.", Toast.LENGTH_SHORT).show()
                        }
                        duracionInt == null || duracionInt <= 0 -> {
                            Toast.makeText(context, "La duración debe ser un número entero positivo.", Toast.LENGTH_SHORT).show()
                        }
                        intensidadSeleccionada == null -> {
                            Toast.makeText(context, "Debe seleccionar una intensidad.", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            // Si todo es válido, calculamos
                            val caloriasBase = actividadSeleccionada!!.caloriasPorMinuto
                            val factorIntensidad = intensidadSeleccionada!!.factor
                            val caloriasQuemadas = caloriasBase * duracionInt * factorIntensidad
                            resultado = "Calorías quemadas: %.1f kcal".format(caloriasQuemadas)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular Calorías")
            }
            Spacer(modifier = Modifier.height(24.dp))

            // MOSTRAR RESULTADO
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