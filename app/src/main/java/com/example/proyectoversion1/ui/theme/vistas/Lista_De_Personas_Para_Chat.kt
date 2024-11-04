package com.example.proyectoversion1.ui.theme.vistas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectoversion1.R
import com.example.proyectoversion1.modelo.Perfil
import com.example.proyectoversion1.navegacion.BottomCircles
import com.example.proyectoversion1.ui.theme.backgroundDark
import com.example.proyectoversion1.ui.theme.backgroundLight
@Composable
fun ChatProfileList(perfilList: List<Perfil>, navController: NavHostController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            ChatTopAppBar()
        },
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            items(perfilList) { perfil ->
                ProfileCard(
                    nombre = perfil.nombre,
                    edad = perfil.edad,
                    pais = perfil.pais,
                    genero = perfil.genero,
                    fotoResourceId = perfil.fotoResourceId ?: R.drawable.nata, // Usa un valor por defecto
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.logo_app),
                    contentDescription = null,
                    modifier = modifier
                        .padding(8.dp)
                        .size(64.dp)

                )
                Text(
                    text = "MAYCHAT", // Cambiado a "MAYCHAT"
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }
    )
}


/*
@Preview(showBackground = true)
@Composable
fun ShowChatProfileList() {
    ChatProfileList(
        perfilList = listOf(
            Perfil("Juan Pérez", 25, "México", "Masculino", null),
            Perfil("María López", 30, "Argentina", "Femenino", null),
            Perfil("Carlos Gómez", 28, "Colombia", "Masculino", null),
            Perfil("Juan Pérez", 25, "México", "Masculino", null),
            Perfil("María López", 30, "Argentina", "Femenino", null),
            Perfil("Carlos Gómez", 28, "Colombia", "Masculino", null),
            Perfil("Juan Pérez", 25, "México", "Masculino", null),
            Perfil("María López", 30, "Argentina", "Femenino", null),
            Perfil("Carlos Gómez", 28, "Colombia", "Masculino", null)
        ),
        modifier = Modifier.fillMaxSize()
    )
}
*/