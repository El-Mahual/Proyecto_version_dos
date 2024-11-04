package com.example.proyectoversion1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectoversion1.modelo.Perfil
import com.example.proyectoversion1.navegacion.NavigationGraph
import com.example.proyectoversion1.ui.theme.ProyectoVersion1Theme
import com.example.proyectoversion1.ui.theme.vistas.ChatProfileList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoVersion1Theme {
                NavigationGraph()

            }
        }
    }
}


/*
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