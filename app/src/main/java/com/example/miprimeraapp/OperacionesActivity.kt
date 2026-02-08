package com.example.miprimeraapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OperacionesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_operaciones)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtValor1 = findViewById<EditText>(R.id.edtValor1)
        val edtValor2 = findViewById<EditText>(R.id.edtValor2)
        val btnOperar = findViewById<Button>(R.id.btnOperar)
        val txvResultado = findViewById<TextView>(R.id.txvResultado)

        btnOperar.setOnClickListener {
            val num1Text = edtValor1.text.toString() //Extraer texto de la lista
            val num2Text = edtValor2.text.toString()

            if(num1Text.isNotEmpty() && num2Text.isNotEmpty()){//Comparar que no esté vacío
                val num1 = num1Text.toInt()//Convertir texto a número
                val num2 = num2Text.toInt()
                val suma = num1 + num2
                txvResultado.text = "Resultado: $suma"

            }else{
                Toast.makeText(
                    this,
                    "Ingresa los valores a operar",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}