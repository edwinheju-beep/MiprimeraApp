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
import com.example.miprimeraapp.remote.RetrofitClient
import retrofit2.Call

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

    private fun loginAPI(usuario: String, contra: String) {

        val datos = mapOf(
            "username" to usuario,
            "contra" to contra
        )

        RetrofitClient.instance.login(datos)
            .enqueue(object : retrofit2.Callback<Map<String, Any>> {

                override fun onResponse(
                    call: Call<Map<String, Any>>,
                    response: retrofit2.Response<Map<String, Any>>
                ) {
                    if (response.isSuccessful) {

                        val body = response.body()

                        if (body?.containsKey("usuario") == true) {

                            Toast.makeText(
                                this@LoginActivity,
                                "Bienvenido $usuario",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent = Intent(this@LoginActivity, MenuActivity::class.java)
                            startActivity(intent)

                        } else {

                            val error = body?.get("error")?.toString()
                            Toast.makeText(
                                this@LoginActivity,
                                error ?: "Credenciales incorrectas",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Error del servidor",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Map<String, Any>>, t: Throwable) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Error de conexión: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

}