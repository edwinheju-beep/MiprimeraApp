package com.example.miprimeraapp

import android.app.DatePickerDialog
import java.util.Calendar
import android.os.Bundle
import retrofit2.Call
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimeraapp.model.Usuario
import com.example.miprimeraapp.remote.RetrofitClient

class RegistroActivity : AppCompatActivity() {

    private lateinit var edtUsername: EditText
    private lateinit var edtCorreo: EditText
    private lateinit var edtTelefono: EditText
    private lateinit var edtDpi: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val edtNombre = findViewById<EditText>(R.id.edtNombre)
        edtUsername = findViewById(R.id.edtUsuario)
        edtCorreo = findViewById(R.id.edtCorreo)
        edtTelefono = findViewById(R.id.edtTelefono)
        edtDpi = findViewById(R.id.edtDpi)
        val edtContra = findViewById<EditText>(R.id.edtContra)
        val edtConfContra = findViewById<EditText>(R.id.edtConfirma)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrarse)

        val edtFechaNac = findViewById<EditText>(R.id.edtFecha)


        //Para colocar un calendario de seleccion
        edtFechaNac.setOnClickListener {

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->

                    val dia = String.format("%02d", selectedDay)
                    val mes = String.format("%02d", selectedMonth + 1)

                    val fecha = "$dia/$mes/$selectedYear"
                    edtFechaNac.setText(fecha)
                },
                year, month, day
            )

            //Evitar fechas futuras
            datePickerDialog.datePicker.maxDate = System.currentTimeMillis()

            datePickerDialog.show()
        }


        btnRegistrar.setOnClickListener {
            val nombre = edtNombre.text.toString()
            val username = edtUsername.text.toString()
            val correo = edtCorreo.text.toString()
            val telefono = edtTelefono.text.toString()
            val fechaTexto = edtFechaNac.text.toString()
            val partes = fechaTexto.split("/")
            val fechanac = if (partes.size == 3) {
                "${partes[2]}-${partes[1]}-${partes[0]}"
            } else {
                fechaTexto
            }
            val dpi = edtDpi.text.toString()
            val contra = edtContra.text.toString()
            val confcontra = edtConfContra.text.toString()

            if (nombre.isNotEmpty() &&
                username.isNotEmpty() &&
                correo.isNotEmpty() &&
                telefono.isNotEmpty() &&
                fechanac.isNotEmpty() &&
                dpi.isNotEmpty() &&
                contra.isNotEmpty() &&
                confcontra.isNotEmpty() &&
                contra.equals(confcontra)
            ) {
                registrarUsuario(nombre, username, correo, telefono, fechanac, dpi, contra)
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registrarUsuario(
        nombre: String,
        username: String,
        correo: String,
        telefono: String,
        fechanac: String,
        dpi: String,
        contra: String
    ) {
        val usuario = Usuario(
            id = null,
            nombre = nombre,
            username = username,
            correo = correo,
            telefono = telefono,
            fechanac = fechanac,
            dpi = dpi,
            contra = contra
        )

        RetrofitClient.instance.registrarUsuario(usuario)
            .enqueue(object : retrofit2.Callback<Map<String, String>> {

                override fun onResponse(
                    call: Call<Map<String, String>>,
                    response: retrofit2.Response<Map<String, String>>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()

                        if (body?.containsKey("mensaje") == true) {
                            Toast.makeText(
                                this@RegistroActivity,
                                body["mensaje"],
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                        } else {
                            Toast.makeText(
                                this@RegistroActivity,
                                body?.get("error") ?: "Error al registrar",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@RegistroActivity,
                            "Error del servidor",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                    Toast.makeText(
                        this@RegistroActivity,
                        "Error de conexión: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

}