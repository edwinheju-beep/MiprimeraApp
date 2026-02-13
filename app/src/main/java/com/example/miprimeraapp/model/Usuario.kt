package com.example.miprimeraapp.model

class Usuario {
    data class Usuario(
        val id: Int?,
        val nombre: String,
        val username: String,
        val correo: String,
        val contra: String? = null
    )
}