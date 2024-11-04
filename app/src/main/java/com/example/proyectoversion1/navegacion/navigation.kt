package com.example.proyectoversion1.navegacion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectoversion1.ui.theme.vistas.ChatProfileList
import com.example.proyectoversion1.ui.theme.vistas.EditProfileView
import com.example.proyectoversion1.ui.theme.vistas.VideoChatScreen
import com.example.proyectoversion1.modelo.Perfil
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()

    // Crear un perfil de ejemplo que se usará en todas las pantallas
    val perfil = remember {
        mutableStateOf(
            Perfil("Juan Pérez", 25, "México", "Masculino", "", null, null) // Cambio aquí
        )
    }

    // Ocultar la barra de navegación al entrar y restaurarla al salir
    DisposableEffect(systemUiController) {
        systemUiController.isNavigationBarVisible = false
        onDispose {
            systemUiController.isNavigationBarVisible = true
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Contenido principal de la pantalla
        Box(modifier = Modifier.weight(1f)) {
            NavHost(
                navController = navController,
                startDestination = "videoChat", // Iniciar en "videoChat"
                modifier = Modifier.fillMaxSize()
            ) {
                composable("chat") {
                    ChatProfileList(
                        perfilList = listOf(
                            Perfil("Juan Pérez", 25, "México", "Masculino", "", null, null),
                            Perfil("María López", 30, "Argentina", "Femenino", "", null, null),
                            Perfil("Carlos Gómez", 28, "Colombia", "Masculino", "", null, null)
                        ),
                        navController = navController
                    )
                }
                composable("videoChat") {
                    VideoChatScreen(navController = navController)
                }
                composable("editProfile") {
                    EditProfileView(
                        perfil = perfil.value, // Pasar el perfil editable
                        navController = navController,
                        onProfileUpdated = { updatedPerfil -> perfil.value = updatedPerfil } // Actualizar perfil
                    )
                }
            }
        }

        // Botones de navegación en la parte inferior de la pantalla
        BottomCircles(navController = navController, perfil = perfil.value)
    }
}

@Composable
fun BottomCircles(navController: NavHostController, perfil: Perfil) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botón circular para el chat
        IconButton(onClick = { navController.navigate("chat") }) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text("Chat", color = Color.White)
            }
        }

        // Botón circular para el video chat
        IconButton(onClick = { navController.navigate("videoChat") }) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text("Video", color = Color.White)
            }
        }

        // Botón circular para editar perfil con imagen de perfil (si está disponible)
        IconButton(onClick = { navController.navigate("editProfile") }) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                // Mostrar la imagen de perfil si existe, de lo contrario mostrar "Edit"
                perfil.fotoBitmap?.let { bitmap ->
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "Imagen de perfil",
                        modifier = Modifier.size(80.dp) // Ajusta el tamaño para que quede dentro del círculo
                    )
                } ?: Text("Edit", color = Color.White) // Texto de respaldo si no hay imagen
            }
        }
    }
}
