package com.example.loginfirebaseapp

data class Product(
    val id: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val stock: Int = 0
) {
    // Firestore necesita este constructor vacío (valores por defecto)
    // para convertir los datos de la nube a este objeto de Kotlin
}