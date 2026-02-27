package com.example.miprimeraapp.model

    data class Usuario(
        val id: Int?,
        val nombre: String,
        val username: String,
        val correo: String,
        val telefono: String,
        val fechanac: String,
        val dpi: String,
        val contra: String? = null
    )
