package com.example.divisas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewParent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.adapters.AdapterViewBindingAdapter
import com.example.divisas.databinding.ActivityMainBinding
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner1 = binding.spinner1
        val spinner2 = binding.spinner2
        val lista = resources.getStringArray(R.array.divisas)

        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,lista)

        var posSipinner1 =""
        var posSpinner2 = ""
        val cantidad = binding.etCantidad
        val resultado = binding.tvResultados
        var total = 0.0

        spinner1.adapter = adapter
        spinner2.adapter = adapter

        spinner1.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>?,view: View?, position: Int, id: Long) {
                posSipinner1 = lista[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(this@MainActivity,"Error",Toast.LENGTH_LONG).show()
            }
        }
        spinner2.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>?,view: View?, position: Int, id: Long) {
                posSpinner2 = lista[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(this@MainActivity,"Error",Toast.LENGTH_LONG).show()
            }
        }

        binding.BtnEnviarConvertir.setOnClickListener {
            Log.i("btn","entro")
            if (cantidad.text.isNotEmpty()){
                when(posSipinner1){
                    "Pesos mexicanos"->{
                        when(posSpinner2){
                            "Pesos mexicanos"->{
                                resultado.text = cantidad.text
                            }
                            "Dolares"->{
                                total =cantidad.text.toString().toFloat()*0.050
                                resultado.text=total.toString()
                            }
                            "Euros"->{
                                total = cantidad.text.toString().toFloat()*0.051
                                resultado.text=total.toString()
                            }
                        }
                    }
                    "Dolares"->{
                        when(posSpinner2){
                            "Pesos mexicanos"->{
                                total = cantidad.text.toString().toFloat()* 20.21
                                resultado.text = cantidad.text
                            }
                            "Dolares"->{
                                resultado.text=cantidad.text
                            }
                            "Euros"->{
                                total=cantidad.text.toString().toFloat() * 1.02
                                resultado.text = total.toString()
                            }
                        }
                    }
                    "Euros"->{
                        when(posSpinner2){
                            "Pesos mexicanos"->{
                                total = cantidad.text.toString().toFloat()* 20.21
                                resultado.text=total.toString()                            }
                            "Dolares"->{
                                total = cantidad.text.toString().toFloat()*0.98
                                resultado.text=total.toString()
                            }
                            "Euros"->{
                                resultado.text=cantidad.text
                            }
                        }
                    }
                }
            }else{
                Toast.makeText(this@MainActivity,"Ingrese monto",Toast.LENGTH_LONG).show()
            }
        }
    }
}