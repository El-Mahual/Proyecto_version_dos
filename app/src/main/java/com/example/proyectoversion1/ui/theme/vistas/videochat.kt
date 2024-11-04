package com.example.proyectoversion1.ui.theme.vistas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectoversion1.R
import com.example.proyectoversion1.navegacion.BottomCircles

@Composable
fun VideoChatScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            ChatTopAppBar()
        },

    ) { paddingValues ->
        Box(modifier = modifier.fillMaxSize().padding(paddingValues)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            )

            Text(
                text = "Presiona para comenzar",
                color = Color.White.copy(alpha = 0.7f),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )

            Box(
                modifier = Modifier
                    .size(150.dp, 200.dp)
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .background(Color.Gray)
            )
        }
    }
}
/*
@Preview(showBackground = true)
@Composable
fun ShowVideoChatScreen() {
    VideoChatScreen()
}

 */