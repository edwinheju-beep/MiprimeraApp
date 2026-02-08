package com.example.miprimeraapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Para redirigir el txvRegistrate a la pantalla registrarse
        val txvRegistrate = findViewById<TextView>(R.id.txvRegistrate)
        txvRegistrate.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        val edtUsuario = findViewById<EditText>(R.id.edtUsuario)
        val edtContra = findViewById<EditText>(R.id.edtContra)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        btnIngresar.setOnClickListener {
            val usuario = edtUsuario.text.toString()
            val contra = edtContra.text.toString()
            navegarMenu(usuario, contra)

        }


    }

    fun navegarMenu(usuario: String, contra: String) {
        val user = "Edwin"
        val pass = "1234"

        if (usuario.isNotEmpty() && contra.isNotEmpty()) {
            if (user.equals(usuario) && pass.equals(contra)) { //Usuario logueado

                Toast.makeText(
                    this,
                    "Bienvenido: $usuario",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Usuario y/o contraseña incorrectos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(this, "Ingrese los datos solicitados", Toast.LENGTH_SHORT).show()
        }
    }

}