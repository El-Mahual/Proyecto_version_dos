package com.example.proyectoversion1.ui.theme.vistas


import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyectoversion1.R
import com.example.proyectoversion1.modelo.Perfil



@Composable
fun EditProfileView(
    perfil: Perfil,
    navController: NavHostController,
    onProfileUpdated: (Perfil) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val selectedImages = remember { mutableStateListOf<Bitmap>() }

    // Lanzador para abrir la galería y seleccionar una imagen para la imagen principal
    val mainImagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let { selectedUri ->
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, selectedUri)
            // Establecer la imagen principal solo
            perfil.fotoBitmap = bitmap // Aquí cambiamos la imagen principal
        }
    }

    // Lanzador para abrir la galería y seleccionar imágenes adicionales
    val additionalImagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let { selectedUri ->
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, selectedUri)
            // Añadir a las imágenes adicionales solo si no excede el límite de 20
            if (selectedImages.size < 20) {
                selectedImages.add(bitmap)
            }
        }
    }

    // Variables de estado para los valores editables
    var nombre by remember { mutableStateOf(perfil.nombre) }
    var edad by remember { mutableStateOf(perfil.edad.toString()) }
    var descripcion by remember { mutableStateOf(perfil.descripcion) }

    Scaffold(
        topBar = { ChatTopAppBar() },
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues, modifier = modifier.fillMaxSize()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                        .padding(16.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        // Imagen de perfil principal con botón de cámara para abrir la galería
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            val mainImage = perfil.fotoBitmap // La imagen principal no debe cambiar accidentalmente
                            mainImage?.let { bitmap ->
                                Image(
                                    bitmap = bitmap.asImageBitmap(),
                                    contentDescription = "Imagen de perfil",
                                    modifier = Modifier.fillMaxSize()
                                )
                            } ?: Image(
                                painter = painterResource(id = R.drawable.nata),
                                contentDescription = "Imagen de perfil",
                                modifier = Modifier.fillMaxSize()
                            )

                            // Botón de cámara para cambiar la imagen principal
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .background(Color.Blue)
                                    .align(Alignment.TopEnd)
                                    .clickable {
                                        mainImagePicker.launch("image/*")
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text("📷", color = Color.White, fontSize = 30.sp)
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Botón cuadrado con símbolo "+" para agregar imágenes adicionales
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.Green)
                                .clickable {
                                    additionalImagePicker.launch("image/*")
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text("+", color = Color.White, fontSize = 30.sp)
                        }

                        // Mostrar imágenes seleccionadas adicionales en un LazyRow (desplazable)
                        LazyRow(
                            modifier = Modifier.padding(top = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(selectedImages.takeLast(19)) { bitmap -> // Excluye la imagen principal
                                Box(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .padding(end = 8.dp)
                                ) {
                                    Image(
                                        bitmap = bitmap.asImageBitmap(),
                                        contentDescription = "Imagen seleccionada",
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(CircleShape)
                                            .border(2.dp, Color.Gray, CircleShape)
                                            .background(Color.LightGray)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Formulario de edición
                        Text("Nombre:", color = Color.White, modifier = Modifier.padding(8.dp))
                        TextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            placeholder = { Text("Escribe el nombre...") }
                        )

                        Text("Edad:", color = Color.White, modifier = Modifier.padding(8.dp))
                        TextField(
                            value = edad,
                            onValueChange = { edad = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            placeholder = { Text("Escribe la edad...") }
                        )

                        Text("Descripción:", color = Color.White, modifier = Modifier.padding(8.dp))
                        TextField(
                            value = descripcion,
                            onValueChange = { descripcion = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            placeholder = { Text("Escribe una descripción...") }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Botón de confirmación para guardar los cambios
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(Color.Green)
                                    .clickable {
                                        // Actualiza el perfil solo si se ha cambiado la imagen principal
                                        val updatedPerfil = perfil.copy(
                                            nombre = nombre,
                                            edad = edad.toIntOrNull() ?: perfil.edad, // Protección contra entrada no válida
                                            descripcion = descripcion,
                                            fotoBitmap = perfil.fotoBitmap // Imagen principal sin cambios accidentales
                                        )
                                        onProfileUpdated(updatedPerfil) // Actualiza el perfil
                                        navController.popBackStack() // Navega hacia atrás
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text("✔️", color = Color.White) // Icono de confirmación
                            }
                        }
                    }
                }
            }
        }
    }
}

//hola

/*
@Preview(showBackground = true)
@Composable
fun ShowEditProfileView() {
    EditProfileView(
        perfil = Perfil("Juan Pérez", 25, "México", "Masculino", null, "Descripción del perfil.")
    )
}

 */

