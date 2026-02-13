package com.example.miprimeraapp.remote

import com.example.miprimeraapp.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("usuarios.php")
    fun obtenerUsuarios(): Call<List<Usuario>>

    @POST("registrar.php")
    fun registrarUsuario(@Body usuario: Usuario): Call<Map<String, String>>

    @POST("login.php")
    fun login(@Body datos: Map<String, String>): Call<Map<String, Any>>
}