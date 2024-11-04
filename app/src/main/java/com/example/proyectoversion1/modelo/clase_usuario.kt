package com.example.proyectoversion1.modelo

import android.graphics.Bitmap

data class Perfil(
    var nombre: String,
    var edad: Int,
    val pais: String,
    val genero: String,
    var descripcion: String = "", // Nuevo campo de descripción
    val fotoResourceId: Int?, // Cambiado de String? a Int?
    var fotoBitmap: Bitmap? = null // Imagen de perfil seleccionada
)
