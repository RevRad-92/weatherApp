package com.example.weatherapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.nio.file.Files.copy


// View Model expone datos del modelo. La Main Page no accede directamente al modelo
class MainPageViewModel : ViewModel() {

    var uiState by mutableStateOf<Estado>(Estado.Vacio)

    // variable observada (remember para q no se pierda al recomponerse, solo cuando está dentro de un composable)
//    val ciudad = mutableStateOf<String>("")
//    val temperatura = mutableStateOf<Int>(0)
//    val descripcion = mutableStateOf<String>("")
//    val st = mutableStateOf<Int>(0)
//    val noHayDatos = mutableStateOf(true)

    private val climaCordoba = Clima(
        ciudad = "Córdoba",
        temperatura = 14,
        estado = "nublado",
        humedad = 18.0F,
        st = 30,
        viento = 30,
        latitud = -314135,
        longitud = -6418105
    )

    private val climaCABA = Clima(
        ciudad = "CABA",
        temperatura = 18,
        estado = "Soleado",
        humedad = 18.0F,
        st = 30,
        viento = 30,
        latitud = -3461315,
        longitud = -5837723
    )

    fun ejecutarIntencion(intencion: Intencion){
        when(intencion){
            Intencion.BorrarTodo-> borrarTodo()
            Intencion.MostrarCABA -> mostrarCABA()
            Intencion.MostrarCordoba -> mostrarCordoba()
            Intencion.MostrarError -> mostrarError()
        }
    }

    private fun mostrarError(){
        uiState = Estado.Error("Este es un error de mentira")
    }
    private fun  borrarTodo(){
        uiState = Estado.Vacio
    }

    private fun mostrarCordoba(){
        uiState = Estado.Exitoso(
            ciudad = climaCordoba.ciudad,
            temperatura = climaCordoba.temperatura,
            descripcion = climaCordoba.estado,
            st = climaCordoba.st
        )

    }

    private fun mostrarCABA(){
        uiState = Estado.Exitoso(
            ciudad = climaCABA.ciudad,
            temperatura = climaCABA.temperatura,
            descripcion = climaCABA.estado,
            st = climaCABA.st
        )
    }
}