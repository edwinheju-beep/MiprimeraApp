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

class ImcActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtPeso = findViewById<EditText>(R.id.edtPeso)
        val edtAltura = findViewById<EditText>(R.id.edtAltura)
        val txvResultado = findViewById<TextView>(R.id.txvResultado)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val txvCategoria = findViewById<TextView>(R.id.txvCategoria)

        btnCalcular.setOnClickListener {
            val pesoText = edtPeso.text.toString()
            val alturaText = edtAltura.text.toString()

            if (pesoText.isNotEmpty() && alturaText.isNotEmpty()) {
                val peso = pesoText.toFloat()
                val altura = alturaText.toFloat()
                val pesoKg = peso * 0.453592
                val alturam = altura / 100
                val imc = pesoKg / (alturam * alturam)
                txvResultado.text = "IMC = ${"%.2f".format(imc)}"
                if (imc < 18.5) {
                    txvCategoria.text = "Por debajo del peso"
                } else if (imc < 25) {
                    txvCategoria.text = "Saludable"
                } else if (imc < 30) {
                    txvCategoria.text = "Sobrepeso"
                } else if (imc < 40) {
                    txvCategoria.text = "Obeso"
                } else if (imc > 40) {
                    txvCategoria.text = "Obesidad de alto riesgo"
                }
            } else {
                Toast.makeText(this, "Ingrese los datos solicitados", Toast.LENGTH_SHORT).show()
            }
        }
    }
}