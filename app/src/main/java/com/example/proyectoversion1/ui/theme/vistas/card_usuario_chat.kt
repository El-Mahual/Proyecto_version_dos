package com.example.proyectoversion1.ui.theme.vistas


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.example.proyectoversion1.R

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
@Composable
fun ProfileCard(
    nombre: String,
    edad: Int,
    pais: String,
    genero: String,
    fotoResourceId: Int? = null, // Cambia a Int? para que sea opcional
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Si no hay foto, se muestra un cuadrado gris como marcador de posición
                if (fotoResourceId != null) {
                    Image(
                        painter = painterResource(id = fotoResourceId),
                        contentDescription = "Foto de perfil de $nombre",
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Cyan, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .background(Color.Magenta)
                            .border(2.dp, Color.Red, CircleShape)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    // Nombre
                    Text(
                        text = nombre,
                        modifier = Modifier.padding(5.dp),
                        style = MaterialTheme.typography.displayMedium
                    )
                    // Edad
                    Text(
                        text = "Edad: $edad",
                        modifier = Modifier.padding(5.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    // País
                    Text(
                        text = "País: $pais",
                        modifier = Modifier.padding(5.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    // Género
                    Text(
                        text = "Género: $genero",
                        modifier = Modifier.padding(5.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardDisplay() {
    ProfileCard(
        nombre = "Juan Pérez",
        edad = 25,
        pais = "México",
        genero = "Masculino",
        fotoResourceId = null // No se pasa imagen
    )
}
