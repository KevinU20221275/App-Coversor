package net.kmontano.convertidorbasico

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Referencias a los elementos del XML
        val txtCantidad = findViewById<EditText>(R.id.txtCantidad)
        val objSpinner = findViewById<Spinner>(R.id.listaConversion)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val lblResultados = findViewById<TextView>(R.id.lblResultados)

        // Configurar el Spinner con opciones
        val opciones = arrayOf("Kilómetros a Metros", "Metros a Kilómetros", "Metros a Centimetros", "Centimetros a Metros", "Metros a Pies", "Pies a Metros")
        val elAdaptador = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opciones)

        elAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        objSpinner.adapter = elAdaptador

        // Acción del botón Calcular
        btnCalcular.setOnClickListener {
            val opcionSeleccionada = objSpinner.selectedItemPosition
            val objCantidad = txtCantidad.text.toString()
            val objLabel = lblResultados

            var cantidad = 0.0
            var resultado = 0.0
            var abreviatura = ""

            try {
                cantidad = objCantidad.toDouble()
                when (opcionSeleccionada){
                    // Kilómetros a Metros
                    0 -> {
                        resultado = cantidad * 1000
                        abreviatura = " M"
                    }
                    // Metros a Kilómetros
                    1 -> {
                        resultado = cantidad / 1000
                        abreviatura = " KMS"
                    }
                    // Metros a Centimetros
                    2 -> {
                        resultado = cantidad * 100
                        abreviatura = " CM"
                    }
                    // Centimetros a Metros
                    3 -> {
                        resultado = cantidad / 100
                        abreviatura = " M"
                    }
                    // Metros a Pies
                    4 -> {
                        resultado = cantidad * 3.28084
                        abreviatura = " FT"
                    }
                    // Pies a Metros
                    5 -> {
                        resultado = cantidad *  0.3048
                        abreviatura = " M"
                    }
                }
            } catch (e: NumberFormatException){
                objLabel.text = "Por favor, ingresa un número valido"

                return@setOnClickListener
            }

            objLabel.text = "Resultado: " + resultado.toString() + abreviatura
        }
    }
}