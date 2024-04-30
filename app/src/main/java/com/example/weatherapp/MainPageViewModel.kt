package com.example.weatherapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainPageViewModel : ViewModel() {
    // variable observada (remember para q no se pierda al recomponerse)
    val ciudad = mutableStateOf<String>("")
    val temperatura = mutableStateOf<Int>(0)
    val descripcion = mutableStateOf<String>("")
    val st = mutableStateOf<Int>(0)
    val noHayDatos = mutableStateOf(false)

    private val climaCordoba = Clima(
        ciudad = "Córdoba",
        temperatura = 14,
        estado = "nublado",
        humedad = 18.0F,
        st = 30,
        viento = 30,
        latitud = 1231333,
        longitud = 121212
    )

    private val climaCABA = Clima(
        ciudad = "CABA",
        temperatura = 18,
        estado = "Soleado",
        humedad = 18.0F,
        st = 30,
        viento = 30,
        latitud = 12314565,
        longitud = 1214574
    )

    fun  borrarTodo(){
        ciudad.value = "0"
        temperatura.value = 0
        descripcion.value = ""
        st.value = 0
        noHayDatos.value = true
    }

    fun mostrarCordoba(){
        ciudad.value = climaCordoba.ciudad
        temperatura.value = climaCordoba.temperatura
        descripcion.value = climaCordoba.estado
        st.value = climaCordoba.st
        noHayDatos.value = false
    }

    fun mostrarCABA(){
        ciudad.value = climaCABA.ciudad
        temperatura.value = climaCABA.temperatura
        descripcion.value = climaCABA.estado
        st.value = climaCABA.st
        noHayDatos.value = false
    }
}