package com.example.weatherapp.presentacion.clima

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.weatherapp.repository.ApiRepository
import com.example.weatherapp.repository.MockRepository
import com.example.weatherapp.repository.Repository
import com.example.weatherapp.repository.models.Ciudad
import com.example.weatherapp.repository.models.Clima
import kotlinx.coroutines.launch

class ClimaViewModel(
    val repository: Repository
) : ViewModel() {

    companion object{
        val factory : ViewModelProvider.Factory  = viewModelFactory{
            initializer {
                val repository = ApiRepository()
                ClimaViewModel(repository)
            }
        }
    }

    var uiState by mutableStateOf<ClimaEstado>(ClimaEstado.Vacio)



    fun ejcutar(intencion: ClimaIntencion){
        when(intencion){
            ClimaIntencion.BorrarTodo-> borrarTodo()
            ClimaIntencion.MostrarCordoba -> mostrarCordoba()
            ClimaIntencion.MostrarError -> mostrarError()
            ClimaIntencion.MostrarCABA -> mostrarCaba()
        }
    }

    private fun mostrarError(){
        uiState = ClimaEstado.Error("Este es un error de mentira")
    }
    private fun  borrarTodo(){
        uiState = ClimaEstado.Vacio
    }

    private fun mostrarCaba(){

    }

    private fun mostrarCordoba(){

        ClimaEstado.Cargando
        viewModelScope.launch {
            val cordoba = Ciudad(name = "Cordoba", lat = -31.4135, lon = -64.18105, state = "Ar")
            try{
                val clima = repository.getClima(cordoba)
                ClimaEstado.Exitoso(
                    ciudad = clima.name ,
                    temperatura = 10.0,//clima.main.temp,
                    descripcion = "asd",//clima.weather.first().description,
                    st = 10.2//clima.main.feelsLike,
                )
            } catch (exception: Exception){
                ClimaEstado.Error("Error")
            }


        }

    }


}