package com.example.firstpracticeexample.presentation.catalogue

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack


// data class:
data class Auto(
    val id: Int,
    val marca: String,
    val modelo: String,
    val precioAproximado: Double,
    val imageUrl: String // URL de la imagen del auto
)

val listaDeAutos = listOf(
    Auto(1, "Ferrari", "SF90 Stradale", 507000.0, "https://cdn.ferrari.com/cms/network/media/img/resize/5db99942a63cc13e517004f1-ferrari-sf90-stradale-design-focus_07?width=768"),
    Auto(2, "Lamborghini", "Revuelto", 600000.0, "https://www.lamborghini.com/sites/it-en/files/DAM/lamborghini/facelift_2019/model_gw/revuelto/2023/08_07/gallery/revuelto_gallery_01.jpg"),
    Auto(3, "Porsche", "911 GT3 RS", 248000.0, "https://files.porsche.com/filestore/image/multimedia/none/992-gt3-rs-modelimage-sideshot/model/cfbb8ed3-6213-11ed-80f7-005056bbdc38/porsche-model.png"),
    Auto(4, "McLaren", "720S", 310500.0, "https://mclaren.scene7.com/is/image/mclaren/DSC00129_6:16x9?wid=1920&hei=1080"),
    Auto(5, "Bugatti", "Chiron", 3300000.0, "https://www.bugatti.com/media/images/1600/chiron-pur-sport_header_02.jpg")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogueScreen(navController: NavController) {
    // Calculamos el costo total
    val costoTotal = listaDeAutos.sumOf { it.precioAproximado }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de Autos Deportivos") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Regresar")
                    }
                }
            )
        }
    ) { paddingValues ->
        // Usamos una LazyColumn para mostrar la lista de forma eficiente
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp) // Padding para toda la lista
        ) {
            // Creamos un ítem de la lista por cada auto en nuestros datos
            items(listaDeAutos) { auto ->
                AutoCard(auto = auto)
            }

            // Ítem final para mostrar el costo total
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Costo Total de todos los autos: $%,.2f USD".format(costoTotal),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        }
    }
}


@Composable
fun AutoCard(auto: Auto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            // Imagen del auto
            AsyncImage(
                model = auto.imageUrl,
                contentDescription = "Imagen de ${auto.marca} ${auto.modelo}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop // Para que la imagen cubra el espacio
            )

            // Información del auto
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "${auto.marca} ${auto.modelo}",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Precio Aprox: $%,.2f USD".format(auto.precioAproximado),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}